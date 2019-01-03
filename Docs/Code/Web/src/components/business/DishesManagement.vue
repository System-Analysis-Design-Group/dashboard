<template lang="pug">
  .dishes-management
    .types-selector
      span.label 餐品分类：
      el-cascader(
        placeholder="选择类别"
        :options="options",
        v-model="selectedOptions",
        change-on-select,
        @change="searchDishes"
      )
      el-button.edit-button(icon="el-icon-more-outline", circle, title="编辑分类", @click="editOptionVisible = true")
    .content
      el-card.dishes-item(
        shadow="hover",
        :body-style="{ padding: '0px' }",
      )
        img.image(src="http://element-cn.eleme.io/static/hamburger.50e4091.png")
        .item-content
          .name 鸡蛋瘦肉肠+手工沙县蒸饺(十只)
          .price
            span.cur-price ￥11
            span.old-price ￥15
        .item-operation(title="更多操作", @click="editDialogVisible = true")
          .edit-button.el-icon-more-outline
      el-card.dishes-item.dishes-item--add(
        shadow="hover",
        :body-style="{ padding: '0px' }",
      )
        .el-icon-plus.add-button
    el-dialog.option-dialog(v-if="editOptionVisible", title="编辑餐品分类", :visible.sync="editOptionVisible")
      el-tag.option-item(
        v-for="op in options",
        :key="op.label"
        closable,
        :disable-transitions="false",
        @close="removeOption(op)",
      ) {{op.label}}
      el-input.new-option-input(
        v-if="optionInputVisible",
        v-model="optionInputValue",
        ref="saveOptionInput"
        size="small"
        @keyup.enter.native="addOption"
        @blur="addOption"
      )
      el-button.new-option-button(v-else size="small" @click="showInput") + 新建分类
        
    el-dialog(v-if="editDialogVisible", title="编辑餐品", :visible.sync="editDialogVisible")
      //- .info-container
      //-   .info-item(
      //-     v-for="item in editSpeciesInfo.metadataList"
      //-   )
      //-     img.img(:src="item.path ? '/api' + item.path.substring(1) : require('../assets/no_img.png')")
      //-     .description {{item.description}}
      //-     el-button.operation(type="danger", icon="el-icon-delete", @click="deleteMetadataById(item.id)" , circle)
      //- .new-info
      //-   el-input.description(
      //-     type="textarea"
      //-     :rows="3"
      //-     placeholder="请输入图片描述"
      //-     v-model="uploadData.description"
      //-   )
      //-   el-upload.uploader(
      //-     ref="uploader",
      //-     action="/api/category/metadata",
      //-     :auto-upload="false",
      //-     :headers="uploadHeader",
      //-     :data="uploadData",
      //-     :multiple="false",
      //-     :on-success="handleUploadSuccess",
      //-     :limit="1"
      //-   )
      //-     el-button(slot="trigger", size="small", type="primary") 选取图片
      //-     el-button.button(type="success", size="small", @click="submitUpload") 添加新图片
</template>

<script>
import auth from '@/api/rest/auth.js'

export default {
  data () {
    return {
      options: [{
        value: '1',
        label: '主食'
      }, {
        value: '2',
        label: '小吃'
      }],
      selectedOptions: [],
      editDialogVisible: false,
      editOptionVisible: false,
      optionInputVisible: false,
      optionInputValue: '',
    }
  },
  methods: {
    searchDishes () {
      // TODO: this.selectedOptions
    },
    removeOption (option) {
      this.options = this.options.filter((op) => {
        return op.label !== option.label
      })
      // TODO: update
    },
    addOption () {
      this.optionInputVisible = false
      if (!this.optionInputValue) return;
      if (this.options.find(op => op.label == this.optionInputValue)) return;
      this.options.push({
        label: this.optionInputValue,
        value: this.optionInputValue,
      })
      this.optionInputValue = ''
      // TODO: update
    },
    showInput () {
      this.optionInputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveOptionInput.$refs.input.focus()
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.dishes-management .types-selector {
  padding: 16px 20px;
  background: rgba(0, 0, 0, 0.02);
  text-align: left;
  box-shadow: 0px 0px 3px rgba(0, 0, 0, 0.1) inset;
}
.types-selector .label {
  color: #606266;
}
.types-selector .edit-button {
  margin-left: 16px;
}
.content {
  padding: 8px;
  text-align: left;
}
.content .dishes-item {
  display: inline-block;
  margin: 8px;
  position: relative;
}
.dishes-item .image {
  width: 160px;
  height: 160px;
}
.item-content {
  box-sizing: border-box;
  width: 160px;
  padding: 0px 4px 8px 4px;
}
.item-content .name {
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 4px;
  color: #606266;
  font-size: 14px;
}
.price .cur-price {
  color: #F56C6C;
  font-size: 18px;
}
.price .old-price {
  color: #909399;
  text-decoration: line-through;
  font-size: 12px;
  padding-left: 4px;
}
.dishes-item .item-operation {
  position: absolute;
  right: 4px;
  bottom: 4px;
  border: 1px solid rgba(0, 0, 0, 0.05);
  border-radius: 4px;
  padding: 0px 4px;
  cursor: pointer;
}
.dishes-item--add {
  display: inline-block;
  width: 160px;
  height: 215px;
}
.dishes-item--add {
  text-align: center;
  cursor: pointer;
}
.dishes-item--add .add-button {
  font-size: 50px;
  color: #909399;
  line-height: 215px;
}

/* Option Dialog */
.option-dialog {
  text-align: left;
}
.option-dialog .option-item {
  margin-right: 10px;
  margin-bottom: 10px;
}
.option-dialog .new-option-input {
  width: 90px;
  vertical-align: middle;
}
</style>
