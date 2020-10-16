const mainVm = new Vue({
    el: "#main",
    data: {},
    mounted: function () {
        this.$nextTick(function () {
            this.initData();
        });
    },
    methods: {
        initData: function () {
        },
    }
});