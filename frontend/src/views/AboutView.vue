<template>
  <div class="about">
    <Parametrage />

  </div>
</template>

<script setup>
import ProjetForm from "@/components/ProjetForm.vue";
import Parametrage from "@/components/Parametrage.vue";
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
