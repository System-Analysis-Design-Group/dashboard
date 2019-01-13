<template lang="pug">
  el-card.orders-management
    .types-selector
      span.label 订单分类：
      el-cascader(
        placeholder="选择类别", change-on-select,
        :options="options", v-model="selectedOptions",
      )
    el-table(:data="shownOrdersData", border, style="width: 100%")
      el-table-column(type="index", width="50")
      el-table-column(prop="addressID", label="地址ID")
      el-table-column(prop="date", label="日期")
      el-table-column(label="操作")
        template(slot-scope="scope")
          el-button(@click="handleLook(scope.row)", type="text", size="small") 查看
          el-button(v-if="showButton",@click="handleAccept(scope.row)", type="text", size="small") 接受
          el-button(v-if="showButton", @click="handleReject(scope.row)", type="text", size="small") 拒绝
    order-detail-dialog(title="订单详情", :visible.sync="dialogVisible", :orderId="orderId")
</template>

<script>
import OrdersService from '@/api/rest/orders'
import OrderDetailDialog from './dialogs/OrderDetailDialog'

export default {
  data () {
    return {
      ordersData: [{"userID":124,"storeID":123,"addressID":123,"numGoods":0,"date":"Tue Dec 25 13:47:54 CST 2018","state":1,"id":14,"goodsInfo":[{"num":2,"unitPrice":20.0,"id":3,"dishId":1},{"num":1,"unitPrice":15.0,"id":4,"dishId":2}]}],
      options: [{
        value: OrdersService.PaidedStatus,
        label: '已支付'
      }, {
        value: OrdersService.AcceptedStatus,
        label: '已接受'
      }, {
        value: OrdersService.RejectedStatus,
        label: '已拒绝'
      }],
      selectedOptions: [OrdersService.PaidedStatus],
      dialogVisible: false,
      orderId: 0,
    }
  },
  methods: {
    handleLook (row) {
      this.dialogVisible = true
      this.orderId = row.id
    },
    handleAccept (row) {
      OrdersService.acceptOrder(row.id)
        .catch(err => this.showError("操作失败"))
    },
    handleReject (row) {
      OrdersService.rejectOrder(row.id)
        .catch(err => this.showError("操作失败"))
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
    shownOrdersData () {
      return this.ordersData.filter((order) => order.state === this.selectedOptions[0])
    },
    showButton () {
      return this.selectedOptions.findIndex((op) => op === OrdersService.PaidedStatus) != -1
    },
  },
  components: {
    OrderDetailDialog
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.orders-management {
  margin: 20px;
}
.types-selector {
  margin-bottom: 20px;
  text-align: left;
}
</style>
