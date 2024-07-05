<template>
  <div>
    <div>
      <el-form ref="form" :model="admin" label-width="80px">
        <el-form-item label="">
          <el-avatar
            style="width: 100px; height: 100px"
            src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
          ></el-avatar>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input style="width: 200px" v-model="admin.username"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input
            style="width: 200px"
            type="password"
            show-password
            v-model="admin.password"
          ></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input style="width: 200px" v-model="admin.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="admin.gender" placeholder="请选择性别">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入职时间">
          <el-input style="width: 200px" v-model="admin.entrydate"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">更新</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      admin: {},
    };
  },
  mounted() {
    let id = window.sessionStorage.getItem("user");
    console.log(id);
    this.$http.get("http://10.2.35.44:8080/emps/" + id).then((result) => {
      this.admin = result.data.data;
      console.log(this.admin);
    });
  },
  methods:{
    onSubmit(){ 
    }
  }
};
</script>