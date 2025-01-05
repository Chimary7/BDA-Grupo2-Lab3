<script setup>
const props = defineProps({
  product: {
    type: Object,
    required: true
  },
  imageUrl: {
    type: String,
    default: ''
  }
})

const emits = defineEmits(['productClick'])

const handleClick = () => {
  emits('productClick', props.product.id)
}
</script>

<template>
  <div @click="handleClick" 
       class="h-96 w-88 bg-white m-4 flex items-center justify-center flex-col rounded-lg border-2 border-color-secondary hover:shadow-lg hover:cursor-pointer">
    <img :src="imageUrl" :alt="product.nombre" class="h-1/2 w-1/2 bg-color-primary" />
    <h2 class="text-xl font-bold text-black p-2">{{ product.nombre }}</h2>
    <p class="text-lg text-black p-2">${{ product.precio }}</p>
  </div>
</template>
