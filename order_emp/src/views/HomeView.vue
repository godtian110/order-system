<template>
  <el-container class="home-container">
    <!-- 头部区域 -->
    <el-header>
      <div>
        <img src="../static/img/logo.png" />
        <span style="margin-left: 15px">良辰线上餐厅(员工端)</span>
      </div>
      <div>
        <span style="margin-right: 15px">{{this.admin.name}}</span>
        <el-button type="info" @click="logout" style="margin-right: 15px">退出</el-button>
      </div>
    </el-header>
    <!-- 页面主体 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!-- 菜单 -->
        <el-menu
          default-active="/welcome"
          background-color="#373d41"
          text-color="#fff"
          active-text-color="#409eff"
          :unique-opened="true"
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
        >
          <el-menu-item :index="'/' + path[0]">
            <i class="el-icon-s-custom"></i>
            <span slot="title">用户信息管理</span>
          </el-menu-item>
          <el-menu-item :index="'/' + path[1]">
            <i class="el-icon-dish"></i>
            <span slot="title">菜品信息管理</span>
          </el-menu-item>
          <el-menu-item :index="'/' + path[2]">
            <i class="el-icon-notebook-2"></i>
            <span slot="title">菜品类别管理</span>
          </el-menu-item>
          <el-menu-item :index="'/' + path[3]">
            <i class="el-icon-s-order"></i>
            <span slot="title">订单管理</span>
          </el-menu-item>
          <el-menu-item :index="'/' + path[4]">
            <i class="el-icon-pie-chart"></i>
            <span slot="title">销量情况</span>
          </el-menu-item>
          <el-menu-item :index="'/' + path[5]">
            <i class="el-icon-user-solid"></i>
            <span slot="title">个人中心</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <!-- 内容主体 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
  
  
  <script>
export default {
  data() {
    return {
      admin:{},
      path: {
        0: "welcome",
        1: "food",
        2: "cart",
        3: "order",
        4: "sales",
        5: "user",
      },
      isCollapse: false,
    };
  },
  mounted() {
    let id=window.sessionStorage.getItem("user");
        console.log(id);
        this.$http.get("http://10.2.35.44:8080/emps/"+id).then((result) => {
            this.admin=result.data.data;
            console.log(this.admin);
        });
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    toggleCollapse() {
      //点击按钮收起折叠菜单
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>
  
  <style lang="less" scoped>
.home-container {
  height: 100%;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #ffffff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    margin-left: 15px;
  }
  img {
    height: 50px;
  }
}

.el-aside {
  background-color: #373d41;
  .el-menu {
    border-right: 0;
  }
}
.el-main {
  background-color: #eaedf1;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>