<template>
  <div class="app-container">
    <!-- 实现ECharts -->
    <h3>实现ECharts</h3>
    	<div id="myChart" :style="{width: '1000px',height: '400px'}"></div>


    </div>
  </div>
</template>

<script>
import { listExamine} from "@/api/system/examinechart";
import {getLoansNo} from "@/api/system/loans";

export default {
  name: "Examine",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      loansNoOptions: [],
      // 总条数
      total: 0,
      // 审批表格数据
      examineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审批状态 0 通过，1 驳回字典
      exaStateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: null,
        pageSize: null,
        exaLoaId: null,
        exaName: null,
        exaTime: null,
        exaState: null,
        exaRemark: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
	  data: [
	    { value: 1048, name: 'Search Engine' },
	    { value: 735, name: 'Direct' },
	    { value: 580, name: 'Email' },
	    { value: 484, name: 'Union Ads' },
	    { value: 300, name: 'Video Ads' }
	  ]
    };
  },
  created() {
    this.getList();

  },
  methods: {
    /** 查询审批列表 */
    getList() {
      this.loading = true;
      listExamine(this.queryParams).then(response => {
        this.examineList = response.rows;
		var data = [];
		var b = {name:'',value:0};
		b.name='通过';
		b.value=1;
		data.push(b);
		//0 通过  1 驳回  2银行通过  3 银行驳回
		for(var i=1;i<this.examineList.length;i++){
			var d = {name:'',value:0}
			if(this.examineList[i].exaState==0){
				d.name='通过'
				d.value+=1;
			}
			if(this.examineList[i].exaState==1){
				d.name='驳回'
				d.value+=1;
			}
			if(this.examineList[i].exaState==3){
				d.name='银行通过'
				d.value+=1;
			}
			if(this.examineList[i].exaState==4){
				d.name='银行驳回'
				d.value+=1;
			}
			if(d.name==data[data.length-1].name){
				data[data.length-1].value+=1;
			}else{
				data.push(d);
			}


		}
		console.log(data);
		this.data=data;

		this.Draw();
      });
    },
    Draw(){
		//0 通过  1 驳回  2银行通过  3 银行驳回
    	let myChart = this.$echarts.init(document.getElementById('myChart'))


    	myChart.setOption({
    		title: {
    		    text: '审批结果',
    		    subtext: '',
    		    left: 'center'
    		  },
    		  tooltip: {
    		    trigger: 'item'
    		  },
    		  legend: {
    		    orient: 'vertical',
    		    left: 'left'
    		  },
    		  series: [
    		    {
    		      name: 'Access From',
    		      type: 'pie',
    		      radius: '50%',
    		      data: this.data,
    		      emphasis: {
    		        itemStyle: {
    		          shadowBlur: 10,
    		          shadowOffsetX: 0,
    		          shadowColor: 'rgba(0, 0, 0, 0.5)'
    		        }
    		      },
    		       // 设置值域的标签
              label: {
                 normal: {
                    position: 'outer', // 设置标签位置，默认在饼状图外 可选值：'outer' ¦ 'inner（饼状图上）'
                    formatter: '{b}:{c}' + '个'
                   }
              }
    		    }
    		  ]
    	})
    },
  }
};
</script>
