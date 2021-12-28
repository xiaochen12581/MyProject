<template>
  <div>
    <h2 style="color: #ff7525">书本管理</h2>
    <el-table :data="books" border style="width: 100%">
      <el-table-column fixed prop="bookid" label="书本id" width="80">
      </el-table-column>
      <el-table-column prop="name" label="书本名字" width="230">
      </el-table-column>
      <el-table-column prop="sn" label="书本编号" width="220">
      </el-table-column>
      <el-table-column prop="author" label="作者" width="190">
      </el-table-column>
      <el-table-column prop="publisher" label="出版社" width="180">
      </el-table-column>
      <el-table-column prop="tpublish" label="日期" width="150">
      </el-table-column>
      <el-table-column label="图片" width="140">
        <template slot-scope="scope">
          <img
            style="width: 60px; height: 60px"
            :src="`${$imgurl}/public/showImg/${scope.row.picture}`"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="130">
        <template slot-scope="scope">
          <el-button
            type="success"
            icon="el-icon-edit"
            plain
            @click="update(scope.row.bookid)"
            >修改</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <br />
    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="num"
      @current-change="jump"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "Book",
  props: [],
  data() {
    return {
      books: [],
      total: 0,
      start: 1,
      num: 5,
    };
  },
  methods: {
    update(bookid) {
      this.$router.push({
        path: "/adm/book/bookUp",
        query: {
          bookid,
        },
      });
    },
    jump(pag) {
      this.start = pag;
      let _this = this;
      this.$get(
        "/api/adm/book/findByPage/" + _this.start + "/" + _this.num + ""
      ).then(function (resp) {
        _this.books = resp.data.data.book;
        _this.total = resp.data.data.total;
      });
    },
  },
  mounted() {
    let _this = this;
    this.$postJson(
      "/api/adm/book/findByPage/" + _this.start + "/" + _this.num + ""
    ).then(function (resp) {
      _this.books = resp.data.data.book;
      _this.total = resp.data.data.total;
      // var a = _this.total % _this.num == 0 ? (_this.total / _this.num) : (Math.floor(_this.total / _this
      // 	.num) + 1);
    });
  },
};
</script>

<style>
.c1 {
  background-color: aqua;
}
</style>
