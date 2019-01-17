<template lang="pug">
  .dishes-management
    .types-selector
      span.label 餐品分类：
      el-select(clearable placeholder="选择类别" v-model="selectedOption")
        el-option(v-for="item in options" :key="item.value" :label="item.label" :value="item.value")
    .content
      el-card.dishes-item(
        shadow="hover",
        :body-style="{ padding: '0px' }",
        v-for="dish in shownDishesData",
        :key="dish.id",
      )
        img.image(:src="dish.imagePath")
        .item-content
          .name {{dish.name}}
          .price
            span.cur-price ￥{{dish.cuPrice}}
            span.old-price ￥{{dish.orPrice}}
        .item-operation(title="编辑餐品", @click="showEditFormDialog(dish)")
          .edit-button.el-icon-more-outline
      el-card.dishes-item.dishes-item--add(
        shadow="hover",
        :body-style="{ padding: '0px' }",
        @click.native="showCreateFormDialog",
      )
        .el-icon-plus.add-button

    dish-form-dialog(
      :visible.sync="editDialogVisible",
      :title="isEditForm ? '编辑餐品' : '新建餐品'",
      :isEdit="isEditForm",
      :editData="editData",
      @has-update="loadData"
    )
</template>

<script>
import auth from '@/api/rest/auth.js'
import DishFormDialog from './dialogs/DishFormDialog'
import FormUtils from '@/utils/form'
import UserUtils from '@/utils/user'
import DishesService from '@/api/rest/dishes'

export default {
  data () {
    return {
      dishesData: [],
      selectedOption: '',
      editDialogVisible: false,
      isEditForm: false,
      editData: {},
      loading: null,
    }
  },
  mounted() {
    this.loadData()
  },
  methods: {
    loadData () {
      this.openLoading()
      DishesService.getAllDishes(UserUtils.getStoreId())
        .then(res => this.dishesData = res.data.obj)
        .catch(_ => this.showError("加载数据失败"))
        .finally(_ => this.closeLoading())
    },
    showCreateFormDialog () {
      this.isEditForm = false
      this.editDialogVisible = true
    },
    showEditFormDialog (dish) {
      FormUtils.assignTo(dish, this.editData, ["id", "name", "typeName", "orPrice", "cuPrice", "description"])
      this.isEditForm = true
      this.editDialogVisible = true
    },
    openLoading () {
      this.loading = this.$loading({
        lock: true,
        text: '加载数据中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
    },
    closeLoading () {
      if (!this.loading) return
      this.loading.close()
      this.loading = null
    },
    showError (message) {
      this.$message({
        showClose: true,
        message: message,
        type: 'error'
      })
    },
  },
  computed: {
    options () {
      return [...new Set(this.dishesData.map(dish => dish.typeName))].map(op => {
        return {
          value: op,
          label: op
        }
      })
    },
    shownDishesData () {
      return !this.selectedOption ? this.dishesData : this.dishesData.filter(dishes => dishes.typeName === this.selectedOption)
    }
  },
  components: {
    'dish-form-dialog': DishFormDialog
  },
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
