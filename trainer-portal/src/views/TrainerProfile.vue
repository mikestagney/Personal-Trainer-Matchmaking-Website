<template>
<default-layout>
    <div class="container">
        <div class="row">
            <div class="col">
                <p id="name">{{trainerprofile.firstName}} {{trainerprofile.lastName}}</p>
                <p>{{trainerprofile.city}}, {{trainerprofile.state}}</p>
            </div>
            <div class="col">
                <p>{{trainerprofile.philosophy}}</p>
                <p>{{trainerprofile.price_per_hour}}</p>
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
