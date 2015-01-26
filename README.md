*** Note that this library is in early stages of development and not ready for production ***

# Android Wear Lift 

An Android library built for recording fitness/exercise data on Android Wear devices, for the moment mainly reps. Made extremely easy to drop and use on any Android project, our goal is to provide a module that can help Wear fitness app developers to easily and reliably handle about simple recording (and maybe one day, complex recording). We document both our algorithm and implementation so that you can easily follow along. Enjoy!

Contributors: [Karanbir Randhawa](https://github.com/karanbirrandhawa) and [Anthony Luu](https://github.com/anthonyluu)

Table of Contents
-----------------

- [History](#history)
- [Library](#library)
- [Implementation](#implementation)
- [Algorithm](#algorithms)
- [Data](#data)
- [Exercises](#exercises)
- [Demo](#demo)

## History

We started development for this application some time ago at a hackathon. At first it started off as a fitness tracker application that would accurately log your exercise statistics and allow you to analyze them. However this idea fell out some time after the hackathon. Still, impressed with the effort that we've made we decided to try to release our completed products one by one as libraries and build on them, hopefully to give other developers an easier chance to pick up where we left off. 

## Library

The repository itself can be imported immediately into Android Studio while taking into account that it consists of three different modules:

* `androidwearlift`
* `mobile`
* `wear`

Only the `androidwearlift` module is relevant to the library while the `mobile` and `wear` modules are to create a demo application to use. While importing you can choose to only import the `androidwearlift` library and with Gradle you can feel free to set it as one of your dependencies. 

## Implementation

Our goal is to make it extremely easy for you to get started on using the library. After adding the module `androidwearlift` you can start to use library functions. 

### Rep Counting

The rep-counter is controlled by an internal state machine that is specified by the respective "Manager" class that you use. An example is shown below for `BicepCurlManager`.

Take the class `ExampleSensorEventListener` that extends `SensorEventListener`

      public class ExampleSensorEventListener implements SensorEventListener {
        ..
        BicepCurlManager manager;
        ..
      }
  
This class within its constructor must initialize `manager` in order to prepare ourselves for recording the next set of exercise data. So within `ExampleSensorEventListener`:

      public ExampleSensorEventListener(... args ...) {
        ..
        ..
         manager = new BicepCurlManager() {
          @Override
          protected void onRepIncrease() {
            reps++;
            textView.setText(Integer.toString(reps));
            }
          };
      }
  
`onRepIncrease()` is a function that you implement when using any exercise "manager" class to interpret sensor data. What it is responsible for is ensuring that at the completion of every rep cycle you will be able to execute your own custom code. In the case of th example above, this is to update the value of a `reps` variable and set its text to a TextView to display to the user.

In order to power our internal state machine we need to provide `BicepCurlManager` with the appropriate data. We do this by the `updateEvent` method which allows us to pass a `SensorEvent` to it and interpret it to see things such as the current position, acceleration, etc. In the case of our current example, we would implement the following code:

      @Override
      public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
          manager.updateEvent(event);
        }
      }

Currently you are responsible for ensuring the appropriate values, i.e. if it requires gravity sensor values then you should updateEvent which `getType()` is a gravity sensor. This will likely change soon as we update the library to do this sort of thing for you. The documentation will be updated soon to specify which event values are required for each Manager.

## Algorithms

As we update, we will try to flesh out and explain the logic behind any relatively complex algorithms we use. As you saw above the implementation of this library will mainly stray around you trying to do actions whenever a scenario happens during the exercise. For example, `onRepIncrease()` allows you to execute a function whenever a user completes a rep. This could be congratulations or updating a counter - we leave it up to you.

### The Bicep Curl

This algorithm also applies to: 
- Shoulder Fly

In the example above, we showed you an example implementation of BicepCurlManager, which tries to track a user's movement and data during a bicep curl exercise. This was the first algorithm we developed and is heavily supported by the Android gravity sensor.

The logic behind this can be explained a little better after we introduce our selves to the way normal sensors work on an Android device. In an Android device the x-axis is defined as the axis that runs along the horizontal of the phone while it is in its default orientation. So for example if we placed a Nexus 4/5 device on a table. The x axis would be coming out of the left and right sides, the right side being the positive x axis. The positive z-axis is defined as coming out of the screen with the negative z-axis going through the back of the phone. The y-axis is the cross product of the two. In order to easily explain this to ourselves during our research we drew a small diagram to detail it:

[Diagram1](Rough_Notes/watch_axes.jpeg)

Realizing the nature of an Android device's orientation we can consider the movement of a normal bicep curl. As the user moves their forearm downwards, the x-axis will change position as well. While the forearm is parallel to the body the x-axis is parallel to the body. While the forearm is diagonal to the body, so is the x-axis. We realize that we can equate the direction of x-axis with the forearm of the user, a very usuful fact when analyzing an exercise like the bicep curl where the movement happens along with the forearm. Once again, in order to easily understand this we drew a small diagram of it: 

[Diagram2](Rough_Notes/watch_orientation.jpeg)

The next thing that we consider is gravity, which we consider to be directed "downwards" towards the ground. Using that logic we realize that there will always be a component of gravity that will lie along our x-axis, ranging from 9.81 to 0 (relatively) as our x-axis varies from facing "downwards" to perpindicular to our body to "upwards". Using this change in the value of the component of gravity on the x-axis, we can obtain a good measure for the subject's change in position along our path.

That is to say, using the device's change orientation (mapped out via gravity), we can track where the watch may be within its current rep. If the component of gravity along the x-axis is 9.81 then the arm is being held parallel to the body (either "upwards" or "downwards").  If the component of gravity along the x-axis is less than 9.81 but greater than zero then we can determine that it is diagonal to the body in some way. Once the value is zero then the arm is held perpindicular to the body and the component of gravity acting along the x-axis is zero. During this cycle the positive or negative value will indicate whether the arm is lying above the perpindicular "zero" point or below it, depending on the implementation, allowing us to easily plot out the movement of the arm in terms of a half-circle.

This allows for mathematical modelling of the bicep curl exercise and many other curved exercises due to the nature of this algorithm being useful to them.

## Data

Currently with our library you can record the following fitness data about the user:

* Reps
* Coming Soon

Our rep count is implemented via an internal state machine that will use a (somewhat) customized algorithm for each respective exercise within our supported exercises.

## Exercises

We have a number of exercises we plan to implement. Currently we are taking the safest and most accurate ones and putting them on there. These are the ones that we mainly tested at the hackathon: the bicep curl and the shoulder fly.

## Demo

We've included a demo application that can be installed onto an Android Wear smartwatch (5.0-compliant) and test our algorithms our out for yourselves.
