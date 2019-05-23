package mim.com.dc3scanner2.util.interfaces;

import mim.com.dc3scanner2.util.models.Trabajador;

public interface ProfileLink {
    public void sync();
    public void updateProfilePic();
    public Trabajador shareProfile();
}
