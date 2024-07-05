<template>
  <div>
    <div style="padding: 10px 0">
      <el-input
        style="width: 200px"
        placeholder="请输入用户名"
        suffix-icon="el-icon-search"
        v-model="searchName"
      ></el-input>
      <el-button style="margin-left: 5px" type="primary">搜索</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-table :data="tableData" border stripe>
        <el-table-column type="index" label="序号" width="50">
        </el-table-column>
        <el-table-column prop="name" label="用户名" width="120">
        </el-table-column>
        <el-table-column prop="total" label="总金额" width="50">
        </el-table-column>
        <el-table-column prop="state" label="订单状态" width="120">
        </el-table-column>
        <el-table-column prop="address" label="目的地" width="120">
        </el-table-column>
        <el-table-column prop="time" label="下单时间"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="warning" @click="chu(scope.row)">出餐</el-button>
            <el-button type="primary" @click="detail(scope.row)"
              >详情</el-button
            >
            <el-button type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="订单详情" :visible.sync="dialogTableVisible">
        <el-table :data="gridData">
          <el-table-column
            property="dishName"
            label="菜品名称"
            width="200"
          ></el-table-column>
          <el-table-column
            property="num"
            label="数量"
            width="100"
          ></el-table-column>
          <el-table-column property="price" label="单价"></el-table-column>
        </el-table>
      </el-dialog>
    </div>
  </div>
</template>
    
    <script >
export default {
  data() {
    return {
      searchName: "",
      tableData: [],
      dialogTableVisible: false,
      gridData:[],
    };
  },
  mounted() {
    this.$http.get("http://10.2.35.44:8080/orders").then((res) => {
      console.log(res.data);
      this.tableData = res.data.data;
    });
  },
  methods: {
    chu(row) {
      this.$http.get("http://10.2.35.44:8080/orders/update", {
        params: {
          id: row.id,
          state: "已出餐",
        },
      });
      this.$message({
        message: "出餐完成",
        type: "success",
      });
      window.location.reload();
    },
    detail(row) {
      this.$http.get("http://10.2.35.44:8080/orders/detail/"+row.id).then(res=>{
       this.gridData=res.data.data;
      })
      this.dialogTableVisible = true;
    },
  },
};
</script>
    
<style lang="less" scoped>
</style>