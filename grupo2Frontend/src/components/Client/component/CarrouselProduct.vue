<template>
  <div class="relative w-full overflow-hidden">
    <div
      class="flex transition-transform duration-500"
      :style="{ transform: `translateX(-${activeIndex * 100}%)` }"
    >
      <div
        v-for="(url, index) in urls"
        :key="index"
        class="w-full flex-shrink-0"
      >
        <div class="relative w-full" style="padding-top: 56.25%;"> <!-- Proporción 16:9 -->
          <template v-if="isVideo(url)">
            <video
              :src="url"
              autoplay
              muted
              loop
              controls
              class="absolute top-0 left-0 w-full h-full object-contain"
            ></video>
          </template>
          <template v-else>
            <img
              :src="url"
              alt=""
              class="absolute top-0 left-0 w-full h-full object-contain"
            />
          </template>
        </div>
      </div>
    </div>

    <!-- Botones de navegación -->
    <button
      @click="prevSlide"
      class="absolute left-0 top-1/2 -translate-y-1/2 bg-gray-800 bg-opacity-50 flex items-center justify-center text-white text-white h-12 w-12 rounded-full hover:bg-gray-500 hover:border-gray-500"
    >
      <
    </button>
    <button
      @click="nextSlide"
      class="absolute right-0 top-1/2 -translate-y-1/2 bg-gray-800 text-white bg-opacity-50 flex items-center justify-center text-white h-12 w-12 rounded-full hover:bg-gray-500 hover:border-gray-500"
    >
      >
    </button>

    <!-- Indicadores -->
    <div class="absolute bottom-4 left-1/2 -translate-x-1/2 flex space-x-2">
      <button
        v-for="(url, index) in urls"
        :key="'indicator-' + index"
        @click="goToSlide(index)"
        :class="[ 'w-3 h-3 rounded-full', activeIndex === index ? 'bg-blue-500' : 'bg-gray-300' ]"
      ></button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const props = defineProps({
  urls: {
    type: Array,
    required: true,
  },
});

const activeIndex = ref(0);

const nextSlide = () => {
  activeIndex.value = (activeIndex.value + 1) % props.urls.length;
};

const prevSlide = () => {
  activeIndex.value =
    (activeIndex.value - 1 + props.urls.length) % props.urls.length;
};

const goToSlide = (index) => {
  activeIndex.value = index;
};

const isVideo = (url) => {
  return url.match(/\.(mp4|webm|ogg)$/i);
};
</script>

<style scoped>
/* Asegura que el contenedor de la imagen/video tenga una proporción de 16:9 */
.relative {
  position: relative;
}
</style>
