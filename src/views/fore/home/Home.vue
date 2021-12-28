<template>
  <div>
    <div align="center">
      <input placeholder="请输入需要查询的内容" v-model="key" class="in1" />
      <el-button type="primary" @click="sel()" plain>查询</el-button>
    </div>

    <hr />
    <div>
      <el-carousel :interval="3000" arrow="always">
        <el-carousel-item v-for="(item, idx) in Hot" :key="idx">
          <div align="center">
            <img
              :src="`${$imgurl}/public/showImg/${item.picture}`"
              style="width: 1500px; height: 300px;"
            />
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
    <br />
    <h4>好评最高的五本书</h4>
    <hr style="border: 2px solid red;" />
    <div style="height: 150px;">
      <table style="width: 100%;">
        <tr>
          <td v-for="rate in Rate5" style="text-align: center;">
            <img
              :src="`${$imgurl}/public/showImg/${rate.picture}`"
              style="width: 150px;height: 80px;"
            />
          </td>
        </tr>
        <tr>
          <td v-for="rate in Rate5" style="text-align: center;">
            {{ rate.name }}
          </td>
        </tr>
        <tr>
          <td v-for="rate in Rate5" style="text-align: center;">
            ￥{{ rate.price }}
            <el-button
              class="el-icon-goods"
              @click="buy(rate.bookid)"
              type="success"
              plain
              >加入购物车</el-button
            >
          </td>
        </tr>
      </table>
    </div>
    <br />
    <h4>销量最高的五本书</h4>
    <hr style="border: 2px solid red;" />
    <div style="height: 150px;">
      <table style="width: 100%;">
        <tr>
          <td v-for="sale in Sale5" style="text-align: center;">
            <img
              :src="`${$imgurl}/public/showImg/${sale.picture}`"
              style="width: 150px;height: 80px;"
            />
          </td>
        </tr>
        <tr>
          <td v-for="sale in Sale5" style="text-align: center;">
            {{ sale.name }}
          </td>
        </tr>
        <tr>
          <td v-for="sale in Sale5" style="text-align: center;">
            ￥{{ sale.price }}
            <el-button
              class="el-icon-goods"
              @click="buy(sale.bookid)"
              type="success"
              plain
              >加入购物车</el-button
            >
          </td>
        </tr>
      </table>
    </div>
    <br />
    <h4>意向最高的五本书</h4>
    <hr style="border: 2px solid red;" />
    <div style="height: 150px;">
      <table style="width: 100%;">
        <tr>
          <td v-for="fav in Fav5" style="text-align: center;">
            <img
              :src="`${$imgurl}/public/showImg/${fav.picture}`"
              style="width: 150px;height: 80px;"
            />
          </td>
        </tr>
        <tr>
          <td v-for="fav in Fav5" style="text-align: center;">
            {{ fav.name }}
          </td>
        </tr>
        <tr>
          <td v-for="fav in Fav5" style="text-align: center;">
            ￥{{ fav.price }}
            <el-button
              class="el-icon-goods"
              @click="buy(fav.bookid)"
              type="success"
              plain
              >加入购物车</el-button
            >
          </td>
        </tr>
      </table>
    </div>
    <br />
    <br />
    <br />
  </div>
</template>

<script>
export default {
  name: "",
  props: [],
  data() {
    return {
      key: "",
      Hot: [{ picture: "" }],
      Rate5: [],
      Sale5: [],
      Fav5: [],
    };
  },
  methods: {
    sel() {
      var key = this.key;
      this.$router.push({
        path: "/fore/goods/goods",
        query: {
          key,
        },
      });
    },
    buy(bookid) {
      let fd = new FormData();
      fd.append("bookid", bookid);
      let _this = this;
      this.$postJson("/api/fore/ord/addCart", fd).then(function(resp) {
        _this.$message({
          message: resp.data.msg,
          type: "success",
        });
      });
    },
  },
  mounted() {
    let _this = this;
    //显示热点图片
    this.$get("/api/public/findHot/5/1").then(function(resp) {
      _this.Hot = resp.data.data;
    });
    //点赞前五
    this.$get("/api/public/book/findRate5").then(function(resp) {
      _this.Rate5 = resp.data.data;
    });
    //销量前五
    this.$get("/api/public/book/findSale5").then(function(resp) {
      _this.Sale5 = resp.data.data;
    });
    //意向前五
    this.$get("/api/public/book/findFav5").then(function(resp) {
      _this.Fav5 = resp.data.data;
    });
  },
};
</script>

<style scoped="scoped">
a {
  font-size: 30px;
  text-decoration: none;
  display: inline-block;
  margin-top: 135px;
  cursor: pointer;
}
a:hover {
  color: red;
}
.in1 {
  outline-style: none;
  border: 1px solid #ccc;
  border-radius: 3px;
  padding: 6px 0px;
  width: 280px;
  font-size: 20px;
}
.in1:focus {
  border-color: #66afe9;
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
}
</style>
