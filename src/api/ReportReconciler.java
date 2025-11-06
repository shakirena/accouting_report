package api;

import java.util.List;

/**
 * Интерфейс для сверки совместимости отчетов
 */
public interface ReportReconciler {
   public abstract List<String>  reconcile();
}
