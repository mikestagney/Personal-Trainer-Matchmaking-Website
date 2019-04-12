<template>
    <default-layout>
        <div class="jumbotron h-100 homeHeader shadow">
            <div class="container">
                <div class="site-heading text-light">
                    <h1 class="display-4">Welcome to Trainer Portal</h1>
                    <h3>A Client - Trainer Matcher</h3>
                     <hr class="my-4">
                    <h2 class="lead">Find a personal trainer and create a customized fitness plan that will "work out" for YOU!</h2>
                </div>
                </div>
        </div>
        <div class="row text-center">
            <div class="col subheading p-2 shadow">
                <h4 class="orangeText">Login/Sign Up</h4>
                <hr class="my-3">
            </div>
            <div class="col subheading p-2 ml-1 shadow">
                <h4 class="orangeText">Benefits</h4>
                <hr class="my-3">
            </div>
            <div class="col subheading p-2 ml-1 shadow">
                <h4 class="orangeText">About</h4>
                <hr class="my-3">
            </div>
        </div>
    </default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
    name: 'home',
    components: {
        DefaultLayout
    }

}
</script>

<style>

.homeHeader {
    background-image: url("../assets/FitnessImages/fitnessImage1.jpg");
    background-size: cover;
    background-position: center;
    border-radius: 4px;
    
}

.subheading {
    background-color: #bdd0d6;
    border-radius: 5px;
}

</style>
