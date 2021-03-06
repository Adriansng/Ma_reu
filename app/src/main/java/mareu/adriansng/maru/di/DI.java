package mareu.adriansng.maru.di;

import mareu.adriansng.maru.service_api.DummyReunionApiService;
import mareu.adriansng.maru.service_api.ReunionApiService;

public class DI {

    private static final ReunionApiService service = new DummyReunionApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     *
     * @return service
     */
    public static ReunionApiService getReunionApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     *
     * @return new DummyReunionApiService
     */
    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
