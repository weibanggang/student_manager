<template>
    <div style="margin: auto;width: 90%;height:120px;margin-top:20px;border: 2px solid #262525">
        <div style="width: 15%;float: left;margin-top: 15px;min-width:190px;">
            <img v-if="url"
                 :src="url"
                 width="190" height="80" style="padding:0 15px;float: right"/>
        </div>
        <div style="width: 25%;float: left;">
            <div style="height:120px;font-size: 13px;display: -webkit-box;
                -webkit-box-orient: vertical;overflow: hidden;display: table-cell;vertical-align: middle;">
                {{context}}
            </div>
        </div>
        <div style="width: 15%;font-size: 18px;float: left;text-align: center;line-height: 120px;">￥{{price}}</div>
        <div style="width: 15%;float: left;text-align: center;line-height: 120px;">x {{count}}</div>
        <div style="width: 13%;font-size: 18px;color:red;float: left;text-align: center;line-height: 120px;">￥{{price *
            count}}
        </div>
        <div style="padding:45px 0;right: 10px;">
            <Button type="error" @click="remove(id,price)">删除</Button>
        </div>
    </div>
</template>
<script>
    export default {
        name: "pro",
        props: {
            id:{required: true},
            url: {
                default: "https://img14.360buyimg.com/n0/jfs/t1/163505/2/4355/204443/600e3ce7Ed4d265c7/ea315e39c5551cee.jpg",
                required: true
            },
            price: {
                default: 6868.88,
                required: true
            },
            context: {
                default: "戴尔笔记本电脑dell灵越15-5509 英特尔酷睿i5 15.6英寸高色域全面屏超轻薄本(十一代i5 16G 512GSSD)星空灰",
                required: true
            },
            count: {
                default: "1",
                required: true
            }
        },
        data: function () {
            return {}
        },
        methods: {
            remove(id){
                this.$emit('remove',id);
            }
        }
    }
</script>
