<template>
  <div>
    <div style="padding: 10px 0">
      <el-input
        style="width: 200px;margin-right: 10px;"
        placeholder="请输入名称"
        suffix-icon="el-icon-search"
        v-model="searchName"
      ></el-input>
      
      <el-button type="primary" @click="search" style="margin-left: 15px"
        >搜索</el-button
      >
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="dialogFormVisible1 = true"
        >新增用户<i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-dialog
        title="添加新用户"
        :visible.sync="dialogFormVisible1"
        style="width: 1200px"
      >
        <el-form :model="form">
          <el-form-item label="用户名" label-width="100px">
            <el-input v-model="form.username" autocomplete></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="100px">
            <el-input
              v-model="form.password"
              type="password"
              show-password
              autocomplete
            ></el-input>
          </el-form-item>
          <el-form-item label="昵称" label-width="100px">
            <el-input v-model="form.name" autocomplete></el-input>
          </el-form-item>
          <el-form-item label="性别" label-width="100px">
            <el-select v-model="form.gender" placeholder="请选择性别">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系电话" label-width="100px">
            <el-input v-model="form.phone" autocomplete></el-input>
          </el-form-item>
          <el-form-item label="头像" label-width="100px">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8080/files/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
            >
              <img v-if="form.image" :src="form.image" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="地址" label-width="100px">
            <el-input v-model="form.address" autocomplete></el-input>
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
      <el-table-column prop="username" label="用户名" width="120">
      </el-table-column>
      <el-table-column prop="name" label="昵称" width="120"> </el-table-column>
      <el-table-column prop="gender" label="性别" width="120">
        <template slot-scope="scope">
          {{ scope.row.gender == 1 ? "男" : "女" }}
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" width="120">
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="140">
      </el-table-column>
      <el-table-column label="头像" width="120">
        <!-- slot-scope="scope" -->
        <template slot-scope="scope">
          <img :src="scope.row.image" alt="" width="100px" height="70px" />
        </template>
      </el-table-column>
      <el-table-column prop="address" label="地址" width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="update(scope.row)">编辑</el-button>
          <el-button type="danger" @click="open(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="编辑用户"
      :visible.sync="dialogFormVisible2"
      style="width: 1200px"
    >
      <el-form :model="updateUser">
        <el-form-item label="用户名" label-width="100px">
          <el-input v-model="updateUser.username" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="100px">
          <el-input
            v-model="updateUser.password"
            type="password"
            show-password
            autocomplete
          ></el-input>
        </el-form-item>
        <el-form-item label="昵称" label-width="100px">
          <el-input v-model="updateUser.name" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="100px">
          <el-select v-model="updateUser.gender" placeholder="请选择性别">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" label-width="100px">
          <el-input v-model="updateUser.phone" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="头像" label-width="100px">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess1"
          >
            <img
              v-if="updateUser.image"
              :src="updateUser.image"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="地址" label-width="100px">
          <el-input v-model="updateUser.address" autocomplete></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="update1">确 定</el-button>
      </div>
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
      searchGender:"",
      checked: true,
      tableData: [],
      total: 0,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      form: {
        id: "",
        username: "",
        password: "",
        name: "",
        gender: "",
        phone: "",
        image: "",
        address: "",
      },
      updateUser: {
        username: "",
        password: "",
        name: "",
        gender: "",
        phone: "",
        image: "",
        address: "",
      },
    };
  },
  mounted() {
    //发送异步请求，获取数据
    this.$http
      .get("http://10.2.35.44:8080/users", {
        params: {
          page: this.page,
          pageSize: this.pagesize,
        },
      })
      .then((result) => {
        this.tableData = result.data.data.rows;
        this.total = Number(result.data.data.total);
        console.log(result.data);
      });
  },
  methods: {
    search() {
      if (this.searchName != "")
        this.$http
          .get("http://10.2.35.44:8080/users/" + this.searchName)
          .then((result) => {
            this.tableData = result.data.data;
            console.log(result.data);
          });
      else
        this.$http
          .get("http://10.2.35.44:8080/users", {
            params: {
              page: this.page,
              pageSize: this.pagesize,
            },
          })
          .then((result) => {
            this.tableData = result.data.data.rows;
            this.total = Number(result.data.data.total);
            console.log(result.data);
          });
    },
    handleAvatarSuccess(res) {
      this.form.image = res.data;
      console.log(this.form.image);
    },
    add() {
      console.log("新增用户");
      this.$http
        .post("http://10.2.35.44:8080/users", this.form)
        .then((result) => {
          console.log("新增用户");
          console.log(result.data);
        });
      this.dialogFormVisible1 = false;
      location.reload();
    },
    update(i) {
      this.updateUser = i;
      if (this.updateUser.gender == 1) this.updateUser.gender = "男";
      else this.updateUser.gender = "女";
      this.dialogFormVisible2 = true;
    },
    handleAvatarSuccess1(res) {
      this.updateUser.image = res.data;
      console.log(this.updateUser.image);
    },
    update1() {
      this.$http
        .put("http://10.2.35.44:8080/users", this.updateUser)
        .then((result) => {
          console.log(result.data);
          setTimeout(() => {}, 1);
        });
      this.dialogFormVisible2 = false;
    },
    open(i) {
      this.updateUser = i;
      const id = this.updateUser.id;
      console.log(id);
      this.$confirm("此操作将永久删除该菜品, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http.delete("http://10.2.35.44:8080/users/" + id);
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
        .get("http://10.2.35.44:8080/users", {
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
        .get("http://10.2.35.44:8080/users", {
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
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-align: center;
}
.avatar {
  width: 50px;
  height: 50px;
  border: 1px dashed #d9d9d9;
  text-align: center;
  display: block;
}
</style>