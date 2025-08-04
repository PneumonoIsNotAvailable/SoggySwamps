package net.pneumono.soggy_shrines;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.pneumono.pneumonocore.PneumonoCoreModMenu;

public class SoggyShrinesModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return PneumonoCoreModMenu.getModConfigScreenFactory(SoggyShrines.MOD_ID);
    }
}
