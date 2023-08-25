let app = Vue.createApp({
    data(){
        return {
            isLogin: false,
            comId:""
        }
    },
    mounted(){
        let that = this;
        axios.get('/company/isLogin')
            .then((data)=>{
                that.comId = data.data;
                if(that.comId){
                    that.isLogin = true;
                }else{
                    that.isLogin = false;
                }
            }).catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        vite(){
            let vitelink = "http://localhost:9090/company/vite/?comId="+this.comId;
            navigator.clipboard.writeText(vitelink);
            layer.alert("链接已复制到剪贴板！")
        }
    }
}).mount("#app2");