<template lang="pug">
  div.comments-management
    el-card.comment-item(v-for="comment in comments", :key="comment.id", shadow="never")
      div {{comment.content}}
</template>

<script>
import FormUtils from '@/utils/form'
import UserUtils from '@/utils/user'
import CommentsService from '@/api/rest/comments'

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
    loadData () {
      this.openLoading()
      CommentsService.getCommentsByStore(UserUtils.getStoreId())
        .then(res => {
          this.comments = res.data
        })
        .catch(_ => this.showError("加载数据失败"))
        .finally(_ => this.closeLoading())
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
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .comment-item {
    margin: 10px
  }
</style>
