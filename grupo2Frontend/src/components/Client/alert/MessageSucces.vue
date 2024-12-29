<script setup>;
import { defineProps } from 'vue';

defineProps({
    message: String
});

</script>

<template>
    <div  class="fixed top-0 left-0 w-full h-full flex items-center justify-center z-[9999] bg-black bg-opacity-20">
        <div class="bg-white text-color-quinary p-6 rounded-lg shadow-2xl border-2 border-color-quinary h-1/3 w-1/3 flex flex-col items-center justify-center" :class="{'animate-scale-up': true}">
        <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="100" height="100" viewBox="0 0 24 24" class="fill-green-500 h-3/5 w-full">
            <path d="M 12 2 C 6.5 2 2 6.5 2 12 C 2 17.5 6.5 22 12 22 C 17.5 22 22 17.5 22 12 C 22 10.9 21.8 9.8007812 21.5 8.8007812 L 19.800781 10.400391 C 19.900781 10.900391 20 11.4 20 12 C 20 16.4 16.4 20 12 20 C 7.6 20 4 16.4 4 12 C 4 7.6 7.6 4 12 4 C 13.6 4 15.100391 4.5007812 16.400391 5.3007812 L 17.800781 3.9003906 C 16.200781 2.7003906 14.2 2 12 2 z M 21.300781 3.3007812 L 11 13.599609 L 7.6992188 10.300781 L 6.3007812 11.699219 L 11 16.400391 L 22.699219 4.6992188 L 21.300781 3.3007812 z"></path>
        </svg>
        <p class="text-color-quinary font-bold text-xl">{{ message }}</p>
      </div>
    </div>
</template>

<style scoped>
@keyframes scaleUp {
  0% {
    transform: scale(0.5);
    opacity: 0;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

.animate-scale-up {
  animation: scaleUp 0.5s ease-out forwards;
}
</style>