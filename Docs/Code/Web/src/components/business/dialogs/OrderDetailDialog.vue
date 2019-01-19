<template lang="pug">
  .order-detail-dialog
    el-dialog(:title="title", :visible.sync="dialogVisible")
      el-table(:data="goodsInfo", border)
        el-table-column(property="num", label="数量")
        el-table-column(property="unitPrice", label="单一价格")
        el-table-column(property="dishId", label="餐品ID")
        el-table-column(property="name", label="餐品名字")
</template>

<script>
import DishesService from '@/api/rest/dishes'
import OrdersService from '@/api/rest/orders'

export default {
  name: 'order-detail-dialog',
  props: {
    visible: {
      type: Boolean,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    orderId: {
      type: Number,
      required: true,
    },
  },
  data () {
    return {
      dialogVisible: this.visible,
      goodsInfo: []
    }
  },
  mounted() {
    if (this.visible) this.showOrderDetail()
  },
  methods: {
    async getOrderDatail () {
      let res = await OrdersService.getOrderDetail(this.orderId)
      let order = res.data.obj
      let goodsInfo = order.goodsInfo
      let dishesPromiseArr = goodsInfo.map(good => DishesService.getDishById(good.dishId))
      let dishesInfoRes = await Promise.all(dishesPromiseArr)
      let dishesInfo = dishesInfoRes.map(info => info.data.obj)
      order.goodsInfo.forEach(good => {
        good.detail = dishesInfo.find(dish => dish.id === good.dishId)
        good.name = good.detail.name
      })
      return order
    },
    showOrderDetail () {
      this.getOrderDatail().then(order => {
        this.goodsInfo = order.goodsInfo
      })
    }
  },
  watch: {
    dialogVisible (val) {
      this.$emit('update:visible', val)
    },
    visible (val) {
      if (val != this.dialogVisible) this.dialogVisible = val
      if (this.visible) this.showOrderDetail()
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
