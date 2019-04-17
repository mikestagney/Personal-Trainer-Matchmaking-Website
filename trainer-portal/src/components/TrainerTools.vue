<template>
    <div class="mb-3">
       <div class="row">
           <div class="col">
               <h5 class="text-center">Quick Tools</h5>
               <hr class="my-3">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <router-link to="" exact>
                    <button class="btn btn-lg btn-info">View Clients</button>
                </router-link>
            </div>
            <div class="col">
                <router-link to="" exact>
                    <button class="btn btn-lg btn-info">Create Workouts</button>
                </router-link>
            </div>
            <div class="col">
                <router-link to="" exact>
                    <button class="btn btn-lg btn-info">Messages</button>
                </router-link>
            </div>
            <div class="col">
                <router-link to="" exact>
                    <button class="btn btn-lg btn-info">Edit Profile</button>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'TrainerTools',

}
</script>

<style>

</style>
