@Todo
Feature: TODO List

  Background: User launching browser
    Given user launch the chrome browser
      | chrome |

  @Test1
  Scenario: Verify the user is able to make new todo list
    Given User enters todo list
      | Book Doctor Appointment next week |
      | Books flight tickets On Monday    |
      | Team Lunch today @11.30AM         |
    Then verify the tasks count on the webpage
    And verify the tasks name on the webpage

  @Test2
  Scenario Outline: Verify user is able to delete a task/tasks
    Given User enters todo list
      | <task1> | <task2> |
    When user want to delete tasks
      | <task1> |
    Then verify the task is deleted
      | <task1> |

    Examples:
      | task1    | task2              |
      | Shopping | Dental Appointment |

  @Test3
  Scenario Outline: Verify user is able to edit task/tasks
    Given User enters todo list
      | <taskToFind1> | <taskToFind2> |
    When user edits the task
      | taskToFind1   | taskToReplace1   |
      | <taskToFind1> | <taskToReplace1> |
    Then verify user is not displayed with old tasks
      | <taskToFind1> |
    And verify user is displayed with edited tasks
      | <taskToReplace1> | <taskToFind2> |

    Examples:
      | taskToFind1                  | taskToReplace1                        | taskToFind2                    | taskToReplace2                                     |
      | Dental Appointment on Monday | Dental Appointment  changed to Friday | P1 Registration Date:12thMarch | Check online date - P1 Registration Date:12thMarch |

  @Test4
  Scenario Outline: Verify user is able to check the task
    Given User enters todo list
      | <task1> | <task2> |
    When user check the tasks
      | <task1> |
    Then task count should decrease
    And proper button should appear

    Examples:
      | task1    | task2              |
      | Shopping | Dental Appointment |

  @Test5
  Scenario Outline: Verify User is able to uncheck the checked todo
    Given User enters todo list
      | <task1> | <task2> | <task3> |
    When user check the tasks
      | <task3> | <task2> |
    And user uncheck the tasks checked previously
      | <task2> |
    Then task count should increase after uncheck

    Examples:
      | task1    | task2                                 | task3      |
      | Shopping | Buy Birthday present before this week | Remittance |

  @Test6
  Scenario Outline: Verify User is able to remove the completed todo
    Given User enters todo list
      | <task1> | <task2> | <task3> |
    When user check the tasks
      | <task3> | <task2> |
    And user remove todo from the list
    And completed tasks should not be displayed
      | <task3> | <task2> |

    Examples:
      | task1             | task2                 | task3              |
      | Team meeeting@4PM | Visit doctor tomorrow | Prj Review Meeting |

  @Test7
  Scenario Outline: Verify user can see appropriate todos by different links - ALL, Active & Completed
    Given User enters todo list
      | <task1> | <task2> | <task3> |
    When user check the tasks
      | <task3> | <task2> |
    And user clicks on All
    Then user should see All todos
      | <task1> | <task2> | <task3> |
    When user clicks on Active
    Then user should see only Active todos
      | <task1> |
    When user clicks on Completed
    Then user should see only  Completed todos
      | <task3> | <task2> |

    Examples:
      | task1                | task2                    | task3                          |
      | Code Review tomorrow | HandsOnMeet @Thursday5PM | Money transfer to acc-87897980 |

  @Test8
  Scenario: Verify home page is proper with headings and default content in todos textBox
    Given verify page launched successfully
    Then verify the heading
    And verify the default text in textBox



