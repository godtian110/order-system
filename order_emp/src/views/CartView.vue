<template>
  <div>
    <div style="padding: 10px 0">
      <el-input
        style="width: 200px"
        placeholder="请输入类别名称"
        suffix-icon="el-icon-search"
        v-model="searchName"
      ></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="search"
        >搜索</el-button
      >
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="dialogFormVisible1 = true"
        >新增类别<i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-dialog
        title="添加类别"
        :visible.sync="dialogFormVisible1"
        style="width: 1200px"
      >
        <el-form :model="form">
          <el-form-item label="类别名" label-width="100px">
            <el-input v-model="form.typeName" autocomplete></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <el-table :data="tableData" border stripe>
      <el-table-column type="index" label="序号" width="40"> </el-table-column>
      <el-table-column prop="id" label="编号" width="40"> </el-table-column>
      <el-table-column prop="typeName" label="类名" width="120">
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间"> </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="warning" @click="getCart(scope.row)">编辑</el-button>
          <el-button type="danger" @click="open(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div>
      <el-dialog
        title="更新类别"
        :visible.sync="dialogFormVisible2"
        style="width: 1200px"
      >
        <el-form :model="update1">
          <el-form-item label="类别名" label-width="100px">
            <el-input v-model="update1.typeName" autocomplete></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="updateCart">确 定</el-button>
        </div>
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
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      form: {
        typeName: "",
      },
      update1: {},
    };
  },
  mounted() {
    //发送异步请求，获取数据
    this.$http.get("http://10.2.35.44:8080/cart").then((result) => {
      this.tableData = result.data.data;
      console.log(result.data);
    });
  },
  methods: {
    search() {
      if (this.searchName === "") {
        this.$http.get("http://10.2.35.44:8080/cart").then((result) => {
          this.tableData = result.data.data;
          console.log(result.data);
        });
      } else
        this.$http
          .get("http://10.2.35.44:8080/cart/" + this.searchName)
          .then((result) => {
            this.tableData = result.data.data;
            console.log(result.data);
          });
    },
    add() {
      this.$http
        .post("http://10.2.35.44:8080/cart", this.form)
        .then((result) => {
          this.tableData = result.data.data;
          console.log(result.data);
        });
      this.dialogFormVisible1 = false;
      location.reload();
    },
    getCart(i) {
      this.update1 = i;
      this.dialogFormVisible2 = true;
    },
    updateCart() {
      this.$http.put("http://10.2.35.44:8080/cart", this.update1);
      this.dialogFormVisible2 = false;
    },
    open(i) {
      this.update1 = i;
      const id = this.update1.id;
      console.log(id);
      this.$confirm("此操作将永久删除该分类, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http.delete("http://10.2.35.44:8080/cart/" + id);
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          location.reload();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>
  
  <style lang="less" scoped>
</style>