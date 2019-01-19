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
import UserUtils from '@/utils/user'

export default {
  data () {
    return {
      ordersData: [],
      options: [{
        value: OrdersService.UnpaidStatus,
        label: '未支付'
      }, {
        value: OrdersService.PaidedStatus,
        label: '已支付'
      }, {
        value: OrdersService.AcceptedStatus,
        label: '已接受'
      }, {
        value: OrdersService.RejectedStatus,
        label: '已拒绝'
      }],
      selectedOptions: [OrdersService.UnpaidStatus],
      dialogVisible: false,
      orderId: 0,
      loading: null,
    }
  },
  mounted() {
    this.updateOrdersData()
  },
  methods: {
    updateOrdersData () {
      this.openLoading()
      OrdersService.getAllOrderOfStore(UserUtils.getStoreId())
        .then(res => this.ordersData = res.data.obj)
        .catch(err => this.showError("加载数据失败"))
        .finally(_ => this.closeLoading())
    },
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
