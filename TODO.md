# CogTom-cst
This is an exercise on porting the CogTom cognitive architecture to the CST toolkit.

### Input files
- [x] Sally-Anne Test case
  - [x] Copy over text files, convert to csv
- [ ] Facebook bAbI test cases
  - [ ] Copy over main test cases, convert to csv
- [ ] Reorganize inputs to allow case selection from the user interface

### AgentMind
- [x] Create all Memory Containers
- [x] Create Activation Memory Objects
  - [x] Use activation to synchronize the current mind step 
  - [x] Execute simulation time steps to the end, create stop criteria (in ID codelet?)
- [x] Create and instantiate Codelets, with inputs and outputs
  - [x] ID
  - [x] EDD
  - [x] SAM
  - [x] ToMM

### Create Codelets on Working Memory
- [x] ID Codelet  
  - [x] Organize codelets according to architecture
  - [x] Read entities.csv file
  - [x] Read intentions.csv file
  - [x] Create internal objects for entities and intentions.
  - [x] Populate output Memory Objects for the entities and intentions.
- [x] Affordances codelet
- [x] EDD Codelet
  - [x] Read eye direction data from file
  - [x] Read MemoryContainers from ID
  - [x] Create Attention Memory Objects as output
- [x] SAM Codelet
  - [x] Create base codelet
  - [x] Read data from EDD and ID
  - [x] Create MemoryContainer for Shared Attention 
- [ ] ToMM Codelet
  - [x] Create base codelet
  - [x] Read ID, Affordances, EDD, SAM data 
  - [x] Create Belief MOs in Working Memory and Output
  - [ ] Belief processing based on Intentions/Affordances
  - [x] Start next step of the simulation

### User Interface
- [ ] Re-use MindView to display information about the state of the MindObjects in the simulation

### Open Questions
- [x] Should MOs have information only for the current simulation step? - Solution is to clear out when necessary.
- [x] Implement mechanism to update the MOs on the containers for each simulation step.
- [x] How to maintain the mind step information across containers - MOs maintain the current mindStep.
- [x] How should the EDD Codelet use ID input, since the csv has agent/object information already - use Agents from ID.
- [x] How to properly sinchronize ID and EDD, due to multithreading nature of the toolkit - use Codelet activation.

### Lessons Learned
- [x] Codelets run in separate threads. Do not assume sequential execution.

### Test-Run for Sally-Anne
- [x] Basic test run finished