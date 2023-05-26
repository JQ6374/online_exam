<template>
  <canvas
      ref="canvasElement"
      id="check-code"
      class="check-code"
      :width="width" :height="height"
      @click="initDraw"
  ></canvas>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";

defineProps({
  width: {
    type: Number,
    default: 98
  },
  height: {
    type: Number,
    default: 38
  }
})

onMounted(() => {
  initDraw();
})

const rightCheckCode = ref([]);

// 验证码对象
const canvasElement = ref()

const $emits = defineEmits(['update:checkCode'])

const initDraw = () => {
  draw(canvasElement.value, rightCheckCode.value)
  $emits('update:checkCode', rightCheckCode.value);
}
const draw = (canvas, showNum) => {
  // 获取canvas
  const ctx = canvas.getContext("2d")
  // 获取画布的宽高
  const canvas_width = canvas.width
  const canvas_height = canvas.height
  //  清空之前绘制的内容
  // 0,0清空的起始坐标
  // 矩形的宽高
  ctx.clearRect(0, 0, canvas_width, canvas_height)
  // 开始绘制
  const scode = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,1,2,3,4,5,6,7,8,9,"
  const arrCode = scode.split(",")
  const arrLength = arrCode.length
  for (let i = 0; i < 4; i++) {
    const index = Math.floor(Math.random() * arrCode.length)
    const txt = arrCode[index]//随机一个字符
    showNum[i] = txt.toLowerCase()//转化为小写存入验证码数组
    // 开始控制字符的绘制位置
    const x = 10 + 20 * i //每一个验证码绘制的起始点x坐标
    const y = 20 + Math.random() * 8// 起始点y坐标

    ctx.font = "bold 20px 微软雅黑"
    // 开始旋转字符
    const deg = Math.random * -0.5
    // canvas 要实现绘制内容具有倾斜的效果，必须先平移，目的是把旋转点移动到绘制内容的地方
    ctx.translate(x, y)
    ctx.rotate(deg)
    // 设置绘制的随机颜色
    ctx.fillStyle = randomColor()
    ctx.fillText(txt, 0, 0)

    // 把canvas复原
    ctx.rotate(-deg)
    ctx.translate(-x, -y)
  }
  for (let i = 0; i < 30; i++) {
    if (i < 5) {
      // 绘制线
      ctx.strokeStyle = randomColor()
      ctx.beginPath()
      ctx.moveTo(Math.random() * canvas_width, Math.random() * canvas_height)
      ctx.lineTo(Math.random() * canvas_width, Math.random() * canvas_height)
      ctx.stroke()
    }
    // 绘制点
    ctx.strokeStyle = randomColor()
    ctx.beginPath()
    const x = Math.random() * canvas_width
    const y = Math.random() * canvas_height
    ctx.moveTo(x, y)
    ctx.lineTo(x + 1, y + 1)
    ctx.stroke()

  }
}
const randomColor = () => {
  const r = Math.floor(Math.random() * 256)
  const g = Math.floor(Math.random() * 256)
  const b = Math.floor(Math.random() * 256)
  return "rgb(" + r + "," + g + "," + b + ")"
}
</script>

<style scoped>
.check-code {
  &:hover {
    cursor: pointer;
  }
}
</style>