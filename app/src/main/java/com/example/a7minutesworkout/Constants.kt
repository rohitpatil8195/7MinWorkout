package com.example.a7minutesworkout

object Constants {
    fun defaultExcerciseList():ArrayList<ExcerciseModel> {
        val excerciseList =ArrayList<ExcerciseModel>()
           val JumpingJacks =ExcerciseModel(
               1,
               "Jumping Jacks",
                R.drawable.ic_jumping_jacks,
               false,
               false
           )

        val AbdominalCrunch =ExcerciseModel(
            2,
            "Abdominal crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )

        val High_knees_running_in_place =ExcerciseModel(
            3,
            "High knees running in place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )

        val Lunge =ExcerciseModel(
            4,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false
        )

        val Plank =ExcerciseModel(
            5,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )

        val Push_up =ExcerciseModel(
            6,
            "Push up",
            R.drawable.ic_push_up,
            false,
            false
        )

        val Push_up_and_rotation =ExcerciseModel(
            7,
            "Push up and rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )

        val Side_plank =ExcerciseModel(
            8,
            "Side plank",
            R.drawable.ic_side_plank,
            false,
            false
        )


        val Squat =ExcerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )

        val Step_up_onto_chair =ExcerciseModel(
            10,
            "Step up onto chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )

        val Triceps_dip_on_chair =ExcerciseModel(
            10,
            "Triceps dip on chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )


        val Wall_sit =ExcerciseModel(
            11,
            "Wall sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )



        excerciseList.add(JumpingJacks)
        excerciseList.add(AbdominalCrunch)
        excerciseList.add(High_knees_running_in_place)
        excerciseList.add(Lunge)
        excerciseList.add(Plank)
        excerciseList.add(Push_up)
        excerciseList.add(Push_up_and_rotation)
        excerciseList.add(Side_plank)
        excerciseList.add(Squat)
        excerciseList.add(Step_up_onto_chair)
        excerciseList.add(Triceps_dip_on_chair)
        excerciseList.add(Wall_sit)



        return excerciseList
    }
}