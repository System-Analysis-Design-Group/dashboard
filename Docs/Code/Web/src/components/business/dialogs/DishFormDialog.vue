<template lang="pug">
  .dish-form-dialog
    el-dialog(:title="title", :visible.sync="dialogVisible")
      .form-container
        el-upload.uploader(
          ref="uploader",
          list-type="picture-card",
          :action="uploadUrl",
          :file-list="fileList"
          :auto-upload="false",
          :headers="uploadHeader",
          :multiple="false",
          :on-success="handleUploadFileSuccess",
          :on-error="handleUploadFileFail",
          :limit="1"
        )
          i.el-icon-plus
        el-form.form(ref="form", :model="form", :rules="rules", label-width="100px", label-position="right")
          el-form-item(label="餐品名称", prop="name")
            el-input(v-model="form.name", clearable)
          el-form-item(label="餐品类型", prop="typeName")
            el-input(v-model="form.typeName", clearable)
          el-form-item(label="餐品现价", prop="cuPrice")
            el-input-number.input-number(v-model="form.cuPrice", :precision="2", :step="0.1", :min="0")
          el-form-item(label="餐品原价", prop="orPrice")
            el-input-number.input-number(v-model="form.orPrice", :precision="2", :step="0.1", :min="0")
          el-form-item(label="餐品描述", prop="description")
            el-input(type="textarea", v-model="form.description", :autosize="{ minRows: 3, maxRows: 3 }")
      div(slot="footer")
        el-button.delete-button(v-if="isEdit", type="danger", @click="handleDelete") 删 除
        el-button(@click="dialogVisible = false") 取 消
        el-button(type="primary", @click="handleConfirm('form')") 确 定
</template>

<script>
import DishesService from '@/api/rest/dishes'
import Cache from '@/utils/cache'
import FormUtil from '@/utils/form'
import UserUtils from '@/utils/user'
const TOKEN_KEY = window.config.tokenKey

const defaultFormData = {
  id: 0,
  storeId: UserUtils.getStoreId(),
  name: '',
  typeName: '',
  orPrice: 0,
  cuPrice: 0,
  description: '',
}

export default {
  name: 'dish-form-dialog',
  props: {
    visible: {
      type: Boolean,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    isEdit: {
      type: Boolean,
      default: false,
    },
    editData: {
      type: Object,
    },
  },
  mounted() {
    this.updateFormData()
  },
  data () {
    return {
      dialogVisible: this.visible,
      uploadHeader: {},
      fileList: [],
      form: FormUtil.clone(defaultFormData),
      rules: {
        name: [
          { required: true, message: '请输入餐品名称', trigger: 'change' },
        ],
        typeName: [
          { required: true, message: '请输入餐品类型', trigger: 'change' },
        ],
        orPrice: [
          { type: 'number', required: true, message: '请输入餐品原价', trigger: 'change' },
        ],
        cuPrice: [
          { type: 'number', required: true, message: '请输入餐品现价', trigger: 'change' },
        ],
        description: [
          { required: true, message: '请输入餐品描述', trigger: 'change' }
        ],
      },
      loading: null,
    }
  },
  methods: {
    updateFormData () {
      if (this.isEdit) {
        FormUtil.assignTo(this.editData, this.form)
      } else {
        FormUtil.assignTo(FormUtil.clone(defaultFormData), this.form)
        this.$nextTick(() => this.$refs.form && this.$refs.form.clearValidate())
      }
    },
    handleUploadFileFail (err, file, fileList) {
      this.$refs.uploader.clearFiles()
      this.closeLoading()
      this.showError("上传图片失败")
    },
    handleUploadFileSuccess (response, file, fileList) {
      this.$refs.uploader.clearFiles()
      this.$emit("has-update")
      this.closeLoading()
      this.dialogVisible = false
    },
    handleConfirm (formName) {
      this.$refs[formName].validate((valid) => {
        let hasFile = !!this.$refs.uploader.uploadFiles.length
        if (!hasFile && !this.isEdit) {
          this.showError('请选择图片')
        } else if (!valid) {
          this.showError('请填写完整信息')
        } else {
          let uploadDataPromise
          this.openLoading()
          if (this.isEdit) {
            uploadDataPromise = DishesService.updateDishes(this.form)
          } else {
            uploadDataPromise = DishesService.createDishes(this.form)
          }
          uploadDataPromise.then(res => {
            if (res.obj && obj.id && !this.isEdit) {
              this.form.id = obj.id
            }
            if (hasFile) {
              this.uploadHeader[TOKEN_KEY] = "Bearer " + Cache.getToken()
              this.$refs.uploader.submit()
            } else {
              this.closeLoading()
              this.dialogVisible = false
              this.$emit("has-update")
            }
          }).catch(err => {
            this.closeLoading()
            this.showError("上传数据失败")
          })
        }
      })
    },
    handleDelete () {
      this.$confirm('此操作将永久删除该餐品, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.openLoading()
        DishesService.deleteDishes(this.form.id)
          .then(() => this.$emit("has-update"))
          .catch(() => this.showError("删除失败"))
          .finally(() => this.closeLoading())
      })
    },
    showError (message) {
      this.$message({
        showClose: true,
        message: message,
        type: 'error'
      })
    },
    openLoading () {
      this.loading = this.$loading({
        lock: true,
        text: '请稍等一下',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
    },
    closeLoading () {
      if (!this.loading) return
      this.loading.close()
      this.loading = null
    }
  },
  watch: {
    dialogVisible (val) {
      this.$emit('update:visible', val)
    },
    visible (val) {
      if (val != this.dialogVisible) {
        this.dialogVisible = val
      }
      if (this.isEdit && this.visible) {
        this.updateFormData()
      }
    },
    isEdit (val) {
      this.updateFormData()
    }
  },
  computed: {
    uploadUrl () {
      // return `http://172.18.233.33:9999/dishes/${this.form.id}`
      return `/api${DishesService.ServicePrefix}/dishes/${this.form.id}`
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.form-container .uploader {
  width: 20%;
  margin-right: 5%;
  display: inline-block;
  vertical-align: top;
}

.form-container .form {
  width: 65%;
  display: inline-block;
}
.form-container .input-number {
  width: 100%;
}

.delete-button {
  float: left;
  margin-left: 30px;
}
</style>
