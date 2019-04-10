<template>
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
</template>

<script>
export default {

}
</script>

<style>

</style>
