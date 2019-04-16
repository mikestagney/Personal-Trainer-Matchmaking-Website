<template>
    <default-layout>
        <div class="jumbotron homeHeader shadow" style="height: 450px;">
            <div class="container">
                <div class="site-heading text-light orangeTransparent p-2">
                    <h1 class="display-4 p-2">Welcome to Workout</h1>
                    <h3>A Client - Trainer Matcher</h3>
                </div>
                </div>
        </div>
        <div class="row">
            <div class="col subheading p-2 shadow border border-info">
                <h4 class="orangeText text-center">Login/Sign Up</h4>
                <hr class="my-3">
                <div class="hexa">
                    <div class="hex1">
                        <div class="hex2 shadow">
                            <img src="../assets/FitnessImages/fitnessImage16.jpg" alt="" width="200" height="193" />
                        </div>
                    </div>
                </div>
                <div class="text-center">
                <router-link to="/login" exact>
                    <button class="btn btn-lg btn-info mt-4 pl-4 pr-4">Login</button>
                </router-link>
                </div>
                <div class="text-center">
                <router-link to="/register" exact>
                    <button class="btn btn-lg btn-info mt-4">Register</button>
                </router-link>
                </div>
            </div>
            <div class="col subheading p-2 ml-4 shadow border border-info">
                <h4 class="orangeText text-center">Benefits</h4>
                <hr class="my-3">
                 <div class="hexa">
                    <div class="hex1">
                        <div class="hex2 shadow">
                            <img src="../assets/FitnessImages/fitnessImage18.jpg" alt="" width="200" height="193" />
                        </div>
                    </div>
                </div>
                <p class="mt-4">Proin porttitor ante eget accumsan efficitur. Phasellus sollicitudin, tellus sit amet mattis vehicula, ligula elit vestibulum lectus, sit amet ullamcorper turpis eros ut purus. Nam mollis neque felis. Pellentesque tincidunt justo vel leo eleifend, at sollicitudin nibh imperdiet.</p>
            </div>
            <div class="col subheading p-2 ml-4 shadow border border-info">
                <h4 class="orangeText text-center">About</h4>
                <hr class="my-3">
                <div class="hexa">
                    <div class="hex1">
                        <div class="hex2 shadow">
                            <img src="../assets/FitnessImages/fitnessImage17.jpg" alt="" width="200" height="193" />
                        </div>
                    </div>
                </div>
                <p class="mt-4">Sed in sem et sem imperdiet porta. Duis faucibus nunc leo, quis tempus massa malesuada et.</p>
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
    background-image: url("../assets/FitnessImages/fitnessImage15.jpg");
    background-size: cover;
     background-position: center;
    border-radius: 4px;
    
}

.subheading {
    background-color: rgba(91, 192, 222, 0.2);
    border-radius: 5px;
}

.blueTransparent {
    background-color: rgba(91, 192, 222, 0.5);
}

.orangeTransparent {
    background-color: rgba(218, 140, 24, 0.6);
}

.hexa, .hexa div {
  margin: 0 auto;
  transform-origin: 50% 50%;
  overflow: hidden;
  width: 200px;
  height: 200px;
}
.hexa {
  width: 235px;
  height: 140px;
}
.hexa div {
  width: 100%;
  height: 100%;
}
.hexa {
  transform: rotate(120deg);
}
.hex1 {
  transform: rotate(-60deg);
}
.hex2 {
  transform: rotate(-60deg);
}

  

</style>
