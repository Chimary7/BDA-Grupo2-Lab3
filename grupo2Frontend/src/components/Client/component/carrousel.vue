<template>
    <div class="relative w-full max-w-[1024px] mx-auto"> <!-- Ajustado a 1366px según la imagen -->
      <div class="relative h-[420px] overflow-hidden">
        <div class="absolute inset-0">
            <img 
            v-for="(item, index) in items" 
            :key="index"
            :src="item"
            :class="[
                'absolute w-full h-full object-cover transition-opacity duration-700 ease-in-out',
                index === active ? 'opacity-100' : 'opacity-0'
            ]"
            alt="carousel image"
            />
        </div>
  
        <button 
          class="absolute top-1/2 left-4 -translate-y-1/2 bg-black/50 text-white px-4 py-3 rounded-lg hover:bg-black/70 transition-all duration-300 ease-in-out" 
          @click="prev"
        >
          ←
        </button>
        <button 
          class="absolute top-1/2 right-4 -translate-y-1/2 bg-black/50 text-white px-4 py-3 rounded-lg hover:bg-black/70 transition-all duration-300 ease-in-out" 
          @click="next"
        >
          →
        </button>
  
        <div class="absolute bottom-6 left-1/2 -translate-x-1/2 flex gap-3">
          <span 
            v-for="(_, index) in items" 
            :key="index"
            :class="[
              'w-3 h-3 rounded-full cursor-pointer transition-all duration-300 ease-in-out',
              index === active ? 'bg-white' : 'bg-white/50 hover:bg-white/70'
            ]"
            @click="goToSlide(index)"
          ></span>
        </div>
      </div>
    </div>
  </template>
  
  
  <script setup>
  import { ref, onMounted, onUnmounted } from 'vue'
  import { TransitionRoot } from '@headlessui/vue'
  
  const props = defineProps({
    items: {
      type: Array,
      required: true
    }
  })
  
  const active = ref(0)
  const interval = ref(null)
  
  const next = () => {
    active.value = active.value === props.items.length - 1 ? 0 : active.value + 1
  }
  
  const prev = () => {
    active.value = active.value === 0 ? props.items.length - 1 : active.value - 1
  }
  
  const goToSlide = (index) => {
    active.value = index
  }
  
  const startAutoplay = () => {
    interval.value = setInterval(() => {
      next()
    }, 6000) // Cambiado a 7 segundos
  }
  
  onMounted(() => {
    startAutoplay()
  })
  
  onUnmounted(() => {
    if (interval.value) clearInterval(interval.value)
  })
  </script>
  