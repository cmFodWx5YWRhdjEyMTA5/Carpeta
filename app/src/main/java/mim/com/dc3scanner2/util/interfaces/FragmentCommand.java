package mim.com.dc3scanner2.util.interfaces;

import java.util.List;

import mim.com.dc3scanner2.util.models.Area;
import mim.com.dc3scanner2.util.models.Fotos;
import mim.com.dc3scanner2.util.models.Subarea;

public interface FragmentCommand {
    void executeCommand(int resultCode);

    List<Fotos> getUpdatedList();

    void executeDialog(List<Area> list);

    void executeDialogSubArea(List<Subarea> list);
    void updateRoute(String ruta);

    void syncAdapter();
}
