<template>
  <div>
    <div style="padding: 10px 0">
      <el-input
        style="width: 200px; margin-right: 10px"
        placeholder="请输入菜品名称"
        suffix-icon="el-icon-search"
        v-model="searchName"
      ></el-input>
      <el-input
        style="width: 200px; margin-right: 10px"
        placeholder="请输入菜品最低价格"
        suffix-icon="el-icon-search"
        v-model="low"
      ></el-input>
      <el-input
        style="width: 200px; margin-right: 10px"
        placeholder="请输入菜品最高价格"
        suffix-icon="el-icon-search"
        v-model="height"
      ></el-input>
      <el-button type="primary" @click="search">搜索</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="dialogFormVisible1 = true"
        >新增餐品<i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-dialog
        title="添加餐品"
        :visible.sync="dialogFormVisible1"
        style="width: 1200px"
      >
        <el-form :model="form">
          <el-form-item label="餐品名称" label-width="100px">
            <el-input v-model="form.name" autocomplete></el-input>
          </el-form-item>
          <el-form-item label="餐品价格" label-width="100px">
            <el-input v-model="form.price" autocomplete></el-input>
          </el-form-item>
          <el-form-item label="状态" label-width="100px">
            <el-select v-model="form.status" placeholder="请选择状态">
              <el-option label="未上架" value="0"></el-option>
              <el-option label="已上架" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="类别" label-width="100px">
            <el-select v-model="form.type" placeholder="请选择类别">
              <el-option
                v-for="item in cart"
                :key="item.id"
                :label="item.typeName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="餐品图片" label-width="100px">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8080/files/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
            >
              <img v-if="form.img" :src="form.img" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="描述" label-width="100px">
            <el-input v-model="form.description" autocomplete></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <el-table :data="tableData" border stripe>
      <el-table-column type="index" label="序号" width="50px">
      </el-table-column>
      <el-table-column prop="name" label="菜名" width="120"> </el-table-column>
      <el-table-column prop="price" label="价格" width="120"> </el-table-column>
      <el-table-column prop="status" label="状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.status == 1 ? "已上架" : "未上架" }}
        </template>
      </el-table-column>
      <el-table-column label="图像" width="120">
        <!-- slot-scope="scope" -->
        <template slot-scope="scope">
          <img :src="scope.row.img" alt="" width="100px" height="70px" />
        </template>
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="140">
      </el-table-column>
      <el-table-column prop="description" label="描述" width="140">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="update(scope.row)">编辑</el-button>
          <el-button type="danger" @click="open(scope.row)">删除</el-button>
          <el-button type="warning" @click="comment(scope.row)"
            >查看评论</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="编辑菜品"
      :visible.sync="dialogFormVisible2"
      style="width: 1200px"
    >
      <el-form :model="updateUser">
        <el-form-item label="名称" label-width="100px">
          <el-input v-model="updateUser.name" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="价格" label-width="100px">
          <el-input v-model="updateUser.price" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="状态" label-width="100px">
          <el-select v-model="updateUser.status" placeholder="请选择状态">
            <el-option label="未上架" value="0"></el-option>
            <el-option label="已上架" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类别" label-width="100px">
          <el-select v-model="updateUser.type" placeholder="请选择类别">
            <el-option
              v-for="item in cart"
              :key="item.id"
              :label="item.typeName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格" label-width="100px">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess1"
          >
            <img v-if="updateUser.img" :src="updateUser.img" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="描述" label-width="100px">
          <el-input v-model="updateUser.description" autocomplete></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="update1">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="餐品评价" :visible.sync="dialogTableVisible">
      <el-table :data="gridData">
        <el-table-column
          property="status"
          label="评价"
          width="150"
        ></el-table-column>
        <el-table-column
          property="context"
          label="内容"
          width="150"
        ></el-table-column>
        <el-table-column property="updateTime" label="日期"></el-table-column>
      </el-table>
    </el-dialog>

    <div style="padding: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script >
export default {
  data() {
    return {
      page: 1,
      pagesize: 5,
      searchName: "",
      low: "",
      height: "",
      checked: true,
      tableData: [],
      cart: [],
      total: 0,
      dialogTableVisible: false,
      gridData: [],
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      form: {
        name: "",
        price: "",
        status: "",
        type: "",
        img: null,
        description: "",
      },
      updateUser: {},
    };
  },
  mounted() {
    //发送异步请求，获取数据
    this.$http
      .get("http://10.2.35.44:8080/dishs", {
        params: {
          page: this.page,
          pageSize: this.pagesize,
        },
      })
      .then((result) => {
        this.tableData = result.data.data.rows;
        console.log(result.data.data.rows);
        this.total = Number(result.data.data.total);
      });
    this.$http.get("http://10.2.35.44:8080/cart").then((result) => {
      this.cart = result.data.data;
    });
  },
  methods: {
    search() {
      this.$http
        .get("http://10.2.35.44:8080/dishs", {
          params: {
            page: this.page,
            pageSize: this.pagesize,
            name: this.searchName,
            low:this.low,
            height:this.height
          },
        })
        .then((result) => {
          this.tableData = result.data.data.rows;
          this.total = Number(result.data.data.total);
        });
    },
    handleAvatarSuccess(res) {
      this.form.img = res.data;
      console.log(this.form.img);
    },
    add() {
      this.$http
        .post("http://10.2.35.44:8080/dishs", this.form)
        .then((result) => {
          console.log(result.data);
        });
      this.dialogFormVisible1 = false;
      location.reload();
    },
    update(i) {
      this.updateUser = i;
      if (this.updateUser.status == 1) this.updateUser.status = "已上架";
      else this.updateUser.status = "未上架";
      console.log(this.updateUser);
      this.dialogFormVisible2 = true;
    },
    handleAvatarSuccess1(res) {
      this.updateUser.img = res.data;
      console.log(this.updateUser.img);
    },
    update1() {
      this.$http
        .put("http://10.2.35.44:8080/dishs", this.updateUser)
        .then((result) => {
          console.log(result.data);
          this.$message({
            message: "修改完成",
            type: "success",
          });
          setTimeout(() => {}, 1);
        });
      this.dialogFormVisible2 = false;
    },
    comment(val) {
      let id = val.id;
      this.$http.get("http://10.2.35.44:8080/comment/" + id).then((result) => {
        this.gridData = result.data.data;
      });
      this.dialogTableVisible = true;
    },
    open(i) {
      this.updateUser = i;
      const id = this.updateUser.id;
      console.log(id);
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http.delete("http://10.2.35.44:8080/dishs/" + id);
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
    handleSizeChange(val) {
      this.pagesize = val;
      this.$http
        .get("http://10.2.35.44:8080/dishs", {
          params: {
            page: this.page,
            pageSize: this.pagesize,
          },
        })
        .then((result) => {
          this.tableData = result.data.data.rows;
          this.total = Number(result.data.data.total);
        });
    },
    handleCurrentChange(val) {
      this.page = val;
      this.$http
        .get("http://10.2.35.44:8080/dishs", {
          params: {
            page: this.page,
            pageSize: this.pagesize,
          },
        })
        .then((result) => {
          this.tableData = result.data.data.rows;
          console.log(result.data.data.rows);
          this.total = Number(result.data.data.total);
        });
    },
  },
};
</script>

<style lang="less" scoped>
.el-dialog {
  width: 300px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  text-align: center;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  border: 1px dashed #d9d9d9;
  text-align: center;
  display: block;
}
</style>