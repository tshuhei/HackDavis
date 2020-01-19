package shuhei.matchomate;

import java.util.List;

public class UserItem {
    private String age;
    private String bio;
    private String exerciseField;
    private String experience;
    private String ft;
    private String gymLocation;
    private String in;
    private String nickname;
    private String userType;
    private String weight;
    private String userId;
    private String gender;
    private List<String> likeUserId;
    private List<String> likedUserId;
    private Long avatar;

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public UserItem(String age, String bio, String exerciseField, String experience, String ft, String gymLocation, String in, String nickname
    , String userType, String weight, String userId, String gender, Long avatar){
        this.age = age;
        this.bio = bio;
        this.exerciseField = exerciseField;
        this.experience = experience;
        this.ft = ft;
        this.gymLocation = gymLocation;
        this.in = in;
        this.nickname = nickname;
        this.userType = userType;
        this.weight = weight;
        this.userId = userId;
        this.gender = gender;
        this.avatar = avatar;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getExerciseField() {
        return exerciseField;
    }

    public void setExerciseField(String exerciseField) {
        this.exerciseField = exerciseField;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFt() {
        return ft;
    }

    public void setFt(String ft) {
        this.ft = ft;
    }

    public String getGymLocation() {
        return gymLocation;
    }

    public void setGymLocation(String gymLocation) {
        this.gymLocation = gymLocation;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(List<String> likeUserId) {
        this.likeUserId = likeUserId;
    }

    public List<String> getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(List<String> likedUserId) {
        this.likedUserId = likedUserId;
    }
}
