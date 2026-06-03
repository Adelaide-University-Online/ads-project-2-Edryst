/**
 * File: DegreeData.java
 * Description: Stores course data, course structures and prerequisite information.
 * Author: Edward Still
 * Student ID: 3133105
 * Email ID: a3133105@adelaide.edu.au || edward.still@student.adelaide.edu.au
 * AI Tool Used: ChatGPT for concept explanation, assistance in debugging and code review.
 * This is my own work as defined by the University's Academic Integrity Policy.
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DegreeData {

    // All courses required for the degree
    private ArrayList<String> allCourses;

    // Map each course to its prerequisites
    private HashMap<String, ArrayList<String>> courseStructureMap;

    // Map each course to its number of prerequisites
    private HashMap<String, Integer> numOfPrerequisitesMap;


    // Constructor method
    public DegreeData(
            ArrayList<String> allCourses,
            HashMap<String, ArrayList<String>> courseStructureMap,
            HashMap<String, Integer> numOfPrerequisitesMap
    ) {

        this.allCourses = allCourses;
        this.courseStructureMap = courseStructureMap;
        this.numOfPrerequisitesMap = numOfPrerequisitesMap;
    }

    // Getters
    public ArrayList<String> getAllCourses() {
        return allCourses;
    }

    public HashMap<String, ArrayList<String>> getCourseStructureMap() {
        return courseStructureMap;
    }

    public HashMap<String, Integer> getNumOfPrerequisitesMap() {
        return numOfPrerequisitesMap;
    }

    // Standard Methods

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof DegreeData)) {
            return false;
        }

        DegreeData other = (DegreeData) object;

        return Objects.equals(allCourses, other.allCourses) &&
                Objects.equals(courseStructureMap, other.courseStructureMap) &&
                Objects.equals(numOfPrerequisitesMap, other.numOfPrerequisitesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allCourses, courseStructureMap, numOfPrerequisitesMap);
    }

    @Override
    public String toString() {
        return "DegreeData{" +
                "allCourses=" + allCourses +
                ", courseStructureMap=" + courseStructureMap +
                ", numOfPrerequisitesMap=" + numOfPrerequisitesMap + "}";
    }

}
