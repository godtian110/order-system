<template>
    <div class="login_container">
      <div class="login_box">
        <!-- 头像区域 -->
        <div class="avatar_box">
          <img src="../assets/logo.png" alt="" />
        </div>
        <!-- 登录表单 -->
        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginFormRules"
          label-width="0"
          class="login_form"
        >
          <!-- 用户名 -->
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              prefix-icon="el-icon-user"
            ></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item prop="password">
            <el-input
              type="password"
              v-model="loginForm.password"
              show-password
              prefix-icon="el-icon-lock"
            ></el-input>
          </el-form-item>
          <!-- 按钮 -->
          <el-form-item style="margin-left: 22%;">
            <el-button type="primary" class="btn" @click="login">登录</el-button>
            <el-button type="info" @click="resetLoginForm">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  
  <script>
  export default {
    data() {
      return {
        // 登录表单数据（双向绑定）
        loginForm: {
          username: "jinyong",
          password: "123456",
        },
        // 表单的验证规则
        loginFormRules: {
          username: [
            { required: true, message: "请输入用户名", trigger: "blur" },
            {
              min: 3,
              max: 20,
              message: "长度在 3 到 10 个字符",
              trigger: "blur",
            },
          ],
          password: [
            { required: true, message: "请输入密码", trigger: "blur" },
            {
              min: 6,
              max: 15,
              message: "长度在 3 到 10 个字符",
              trigger: "blur",
            },
          ],
        },
      };
    },
    methods: {
      login() {
        console.log(this.loginForm);
        this.$refs.loginFormRef.validate(async (valid) => {
          if (!valid) return;
          await this.$http
            .post("http://10.2.35.44:8080/emps/login", this.loginForm)
            .then((result) => {
              if (result.data.code != 1)
                return this.$message.error("账号或者密码有误！");
              this.$message.success("登陆成功！");
              console.log(result.data.data);
              window.sessionStorage.setItem("user", result.data.data.id);
              this.$router.push("/home");
            });
        });
      },
      //点击重置表单
      resetLoginForm() {
        // console.log(this);
        this.$refs.loginFormRef.resetFields();
      },
    },
  };
  </script>
  
  <style lang="less" scoped>
  .login_container {
    //   background-color: #2b4b6b;
    height: 100%;
  }
  .login_box {
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 10px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  
    .avatar_box {
      height: 130px;
      width: 130px;
      border: 1px solid #eee;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 10px #ddd;
      position: absolute;
      left: 50%;
      transform: translate(-50%, -50%);
      background-color: #fff;
      img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
      }
    }
  }
  .register {
    margin-bottom: 5px;
    width: 200px;
    margin-top: 75px;
    margin-left: 235px;
  }
  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }
  .btn {
    margin-right: 120px;
  }
  </style>