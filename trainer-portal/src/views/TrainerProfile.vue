<template>
<default-layout>
    <div class="container">
        <div class="row">
            <div class="col">
                <h1>The trainer Id is: {{this.$route.params.Tid}}</h1>
                <p id="name">{{trainerprofile.first_name}} {{trainerprofile.last_name}}</p>
                <p>{{trainerprofile.city}}, {{trainerprofile.state}}</p>
            </div>
            <div class="col">
                <p>{{trainerprofile.philosophy}}</p>
                <p>{{trainerprofile.hourly_price}}</p>
                <p>{{trainerprofile.rating}}</p>
            </div>
        </div>
        <div class="row">
            {{trainerprofile.bio}}
        </div>
        <div class="row">
            {{trainerprofile.certifications}}
        </div>
    </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
    name: "TrainerProfile",
    components: {
        DefaultLayout
    },
}
</script>

<style>

</style>
