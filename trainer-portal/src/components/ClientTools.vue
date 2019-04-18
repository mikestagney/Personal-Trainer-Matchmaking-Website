<template>
    <div class="mb-3 bg-light border border-warning">
       <div class="row">
           <div class="col">
               <h4 class="text-center orangeText pt-3">Quick Tools</h4>
               <hr class="my-3">
            </div>
        </div>
        <div class="row text-center mb-3">
            <div class="col">
                <router-link v-bind:to="{ name: 'workout-plans', params: { UserID: ClientID }}" exact>
                    <button class="text-light btn-lg exclusiveButton">Current Workouts</button>
                </router-link>
                <router-link to="" exact>
                    <button class="text-light btn-lg exclusiveButton ml-2">Messages</button>
                </router-link>
                <router-link to="/directory" exact>
                    <button class="text-light btn-lg exclusiveButton ml-2">View Available Trainers</button>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'TrainerTools',

    props: {
        ClientID: Object, 
    }

}
</script>

<style>
.exclusiveButton {
    background-color: #c71585;
}

</style>