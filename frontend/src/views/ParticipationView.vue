<template>
  <div class="about">
    <Participation />
  </div>
</template>

<script setup>
import Participation from "@/components/Participation.vue";
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
