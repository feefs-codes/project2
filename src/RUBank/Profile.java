package RUBank;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }
    

    @Override
    public int compareTo(Profile profile) {
        if (this.lname.compareTo(profile.lname) == 0) {
            if (this.fname.compareTo(profile.fname) == 0) {
                return this.dob.compareTo(profile.dob);
            }
            return this.fname.compareTo(profile.fname);
        }
        return this.lname.compareTo(profile.lname);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return this.compareTo(profile) == 0;
        }
        return false;
    }

}