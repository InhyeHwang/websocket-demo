let stompClient = null;

const vm = new Vue({
  el: "#app",
  mounted: function () {
    this.$nextTick(function () {

      // 1. SockJS 연결 후 SockJS를 내부에 들고있는 client를 내어준다.
      let socket = new SockJS("/ws");
      stompClient = Stomp.over(socket);

      // 2. connection이 맺어지면 실행된다.
      stompClient.connect(
        {},
        function (frame) {
         console.log("Connected: " + frame);
          stompClient.subscribe("/subscribe/values", function (val) {
            vm.valuesList = JSON.parse(val.body);
          });
        }
      );
    });
  },
  data: function () {
    return {
      valuesList: [],
      connected: false
    };
  }
});
