package org.keeps.digitalpreservation.commonsip.citserms.validator.components;

import org.roda_project.commons_ip2.validator.observer.ValidationObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class ErmsValidatorImpl implements ErmsValidator {

  /**
   * {@link List} of {@link ValidationObserver}.
   */
  private List<ValidationObserver> observers = new ArrayList<>();



  @Override
  public void addObserver(final ValidationObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(final ValidationObserver observer) {
    this.observers.remove(observer);
  }

  public void notifyObserversValidationStarted(final String moduleName, final String id) {
    for (ValidationObserver observer : observers) {
      observer.notifyStartValidationModule(moduleName, id);
      observer.notifyStartStep(id);
    }
  }

  public void notifyObserversFinishModule(final String moduleName) {
    for (ValidationObserver observer : observers) {
      observer.notifyFinishModule(moduleName);
    }
  }

}
