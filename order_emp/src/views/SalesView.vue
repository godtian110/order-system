<template>
  <div>
    <div style="display: flex">
      <div style="margin: 40px">
        <el-date-picker
          style="width: 200px; margin: 10px 10px"
          v-model="month1"
          type="month"
          placeholder="选择月"
        >
        </el-date-picker>
        <el-button type="primary" @click="search1">搜索</el-button>
        <div id="cart"></div>
      </div>
      <div style="margin: 40px; flex: 1">
        <el-date-picker
          style="width: 200px; margin: 10px 10px"
          v-model="month2"
          type="month"
          placeholder="选择月"
        >
        </el-date-picker>
        <el-button type="primary" @click="search2">搜索</el-button>
        <div id="dishes"></div>
      </div>
    </div>
    <div>
      <div style="margin: 40px; flex: 2">
        <el-input
          style="width: 200px; margin-right: 10px"
          placeholder="请输入名称"
          suffix-icon="el-icon-search"
          v-model="dishName"
        ></el-input>
        <el-button type="primary" @click="search3">搜索</el-button>
        <div id="dish"></div>
      </div>
    </div>
  </div>
</template>
  
<script>
import * as echarts from "echarts/core";
// 引入柱状图图表，图表后缀都为 Chart
import { BarChart } from "echarts/charts";
// 引入提示框，标题，直角坐标系，数据集，内置数据转换器组件，组件后缀都为 Component
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent,
} from "echarts/components";
// 标签自动布局、全局过渡动画等特性
import { LabelLayout, UniversalTransition } from "echarts/features";
// 引入 Canvas 渲染器，注意引入 CanvasRenderer 或者 SVGRenderer 是必须的一步
import { CanvasRenderer } from "echarts/renderers";

export default {
  data() {
    return {
      month1: "2024-05",
      month2: "2024-05",
      dishName: "米饭",
      option1: {
        title: {
          text: "分类菜品销量展示",
        },
        tooltip: {},
        xAxis: {
          data: ["主食", "特色套餐", "清爽饮品", "新奇佳肴", "微醺酒水"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [30, 15, 36, 6, 6],
          },
        ],
      },
      option2: {
        title: {
          text: "各个菜品销量展示",
        },
        tooltip: {},
        xAxis: {
          data: ["米饭", "馒头", "面条", "口水鸡", "清爽可乐", "雪碧"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [19, 5, 6, 6, 16, 10],
          },
        ],
      },
      option3: {
        title: {
          text: "单个菜品销量展示",
        },
        tooltip: {},
        xAxis: {
          data: [12, 1, 2, 3, 4, 5],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [19, 5, 6, 6, 16, 10],
          },
        ],
      },
    };
  },
  created() {
    echarts.use([
      TitleComponent,
      TooltipComponent,
      GridComponent,
      DatasetComponent,
      TransformComponent,
      BarChart,
      LabelLayout,
      UniversalTransition,
      CanvasRenderer,
    ]);
  },
  mounted() {
    var myChart = echarts.init(document.getElementById("cart"));
    myChart.setOption(this.option1);
    var myChart2 = echarts.init(document.getElementById("dishes"));
    myChart2.setOption(this.option2);
    var myChart3 = echarts.init(document.getElementById("dish"));
    myChart3.setOption(this.option3);
  },
  methods: {
    search1() {
      if (this.month1) {
        this.option1.series[0].data = [10, 0, 30, 5, 6];
        var myChart = echarts.init(document.getElementById("cart"));
        myChart.setOption(this.option1);
      }
    },
    search2() {
      this.option2.series[0].data = [5, 3, 2, 6, 18, 12];
      var myChart = echarts.init(document.getElementById("dishes"));
      myChart.setOption(this.option2);
    },
    search3() {
      this.option3.series[0].data = [5, 3, 2, 6, 18, 12];
      var myChart = echarts.init(document.getElementById("dish"));
      myChart.setOption(this.option3);
    },
  },
};
</script>
  
<style>
#cart {
  width: 500px;
  height: 350px;
}
#dishes {
  width: 500px;
  height: 350px;
}
#dish {
  width: 500px;
  height: 350px;
}
</style>