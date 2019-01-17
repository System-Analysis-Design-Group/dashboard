<template lang="pug">
  el-card.comments-management
    el-table(:data="comments" stripe)
      el-table-column(prop="customer" label="顾客" width=200)
      el-table-column(prop="target" label="对象" width=200 :filters="filters" :filter-method="filterHandler")
      el-table-column(prop="content" label="评论")
</template>

<script>
import FormUtils from '@/utils/form'
import UserUtils from '@/utils/user'
import CommentsService from '@/api/rest/comments'
import AccountsService from '@/api/rest/accounts'
import DishesService from '@/api/rest/dishes'

export default {
  data () {
    return {
      comments: []
    }
  },
  methods: {
  },
  mounted() {
    this.loadData()
  },
  methods: {
    async loadData () {
      this.openLoading()
      try {
        let comments = (await CommentsService.getCommentsByStore(UserUtils.getStoreId())).data
        let customersPromises = comments.map(comment => AccountsService.getUserInfo(comment.userId))
        let customers = (await Promise.all(customersPromises)).map(res => res.data.obj)
        let dishesPromises = comments.map((comment) => {
          if (comment.dishId == null) {
            return Promise.resolve(null)
          } else {
            return DishesService.getDishById(comment.dishId)
          }
        })
        let dishes = (await Promise.all(dishesPromises)).map((res) => {
          if (res) return res.data.obj
          else return null
        })
        for (let i = 0; i < comments.length; i++) {
          comments[i].customer = customers[i].username
          if (dishes[i])
            comments[i].target = dishes[i].name
          else
            comments[i].target = '餐馆'
        }
        this.comments = comments
      } catch (err) {
        this.showError(err.message)
      } finally {
        this.closeLoading()
      }
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
    filterHandler(value, row, column) {
      return row.target === value;
    }
  },
  computed: {
    filters () {
      return [...new Set(this.comments.map(comment => comment.target))].map(target => {
        return {
          text: target,
          value: target
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .comments-management {
    margin: 20px 10%
  }
</style>
