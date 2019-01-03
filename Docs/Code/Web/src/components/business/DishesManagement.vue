<template lang="pug">
  .dishes-management
    .types-selector
      span.label 餐品分类：
      el-cascader(
        placeholder="选择类别"
        :options="options",
        v-model="selectedOptions",
        change-on-select
      )
      el-button.edit-button(icon="el-icon-more-outline", circle, title="编辑分类")
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
        .item-operation(title="更多操作")
          .edit-button.el-icon-more-outline
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
      selectedOptions: []
    }
  },
  methods: {
    
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
</style>
