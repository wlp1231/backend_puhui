<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import {findData} from "@/api/system/sendchart_index";
import {comCount} from "@/api/system/count";

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object,
      required: false
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  created(){
    this.initQuery();
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initQuery(){
      // ajax 查询数据
      findData().then(response => {
        this.chartData = response.data;
        //console.log('555'+response.data);
      //  this.initChart();
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    },
    setOptions({ expectedData, actualData } = {}) {
      this.chart.setOption({
        title:{
          text:'放款信息',
          left:'center'
        },
        xAxis: {
          type: 'category',
          data: expectedData,
          boundaryGap: false,
          axisTick: {
            show: false
          }
        },
        legend: {
          right:0
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          type: 'value',
          name: '单位（元）',
          axisTick: {
            show: false
          }
        },

        series: [{
          smooth: true,
          type: 'line',
          name:'放款信息',
          data: actualData,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        }]
      })
    }
  }
}
</script>
