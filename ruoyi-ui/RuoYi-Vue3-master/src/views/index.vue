<template>
  <el-calendar v-model="value" style="width:700px" />
</template>

<script lang="ts" setup>
import { ref } from 'vue'
const value = ref(new Date())
</script>
