<template>
    <div>
        <div>
            <div style="height: 360px;width:300px;border: 1px solid rgba(0,0,0,0.18);position: relative;float: left;margin: 20px">
                <div style="height: 200px;width:100%;background-color: white;">
                    <img v-if="url"
                         :src="url"
                         width="300" height="200" style="border:1px solid #d3d3d3;"/>
                </div>
                <div style="width:100%;font-size: 24px;color: red"> $ {{price}}</div>
                <div style="height: 60px;width:100%;font-size: 14px;display: -webkit-box;
                -webkit-box-orient: vertical;overflow: hidden;">
                  {{context}}
                </div>
                <div style="padding: 10px;float: right;right: 1px">
                    <Button type="primary" @click="add(id,price)">加入购物车</Button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        name: "pro",
        props: {
            id:{required:true},
            url: {
                default:"https://img14.360buyimg.com/n0/jfs/t1/163505/2/4355/204443/600e3ce7Ed4d265c7/ea315e39c5551cee.jpg",
                required:true
            },
            price: {
                default: 6868.88,
                required:true
            },
            context: {
                default:"戴尔笔记本电脑dell灵越15-5509 英特尔酷睿i5 15.6英寸高色域全面屏超轻薄本(十一代i5 16G 512GSSD)星空灰",
                required:true
            }
        },
        data: function () {
            return {}
        },
        methods: {
            add(id,price){
                this.$emit('add',{id,price});
            }
        }
    }
</script>
