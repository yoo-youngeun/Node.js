<template>
  <div>
    <button @click="$_callApiAction()">Call Action</button>
    <button @click="otherWayCall()">Other Way Call Action</button>
  </div>
</template>

<script>
export default {
    methods: {
        otherWayCall() {
          this.$_callApiAction();
        }
    }
}
</script>