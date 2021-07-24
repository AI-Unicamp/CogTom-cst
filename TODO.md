# CogTom-cst
This is an exercise on porting the CogTom cognitive architecture to the CST toolkit.

### Input files
- [x] Sally-Anne Test case
  - [x] Copy over text files, convert to csv
- [ ] Facebook bAbI test cases
  - [ ] Copy over main test cases, convert to csv 

### Create Memory Objects/Containers
- [x] Create on AgentMind

### Create Codelets on Working Memory
- [ ] ID Codelet  
  - [x] Organize codelets according to architecture
  - [x] Read entities.csv file
  - [x] Read intentions.csv file
  - [x] Create internal objects for entities and intentions.
  - [x] Populate output Memory Objects for the entities and intentions.
- [ ] Affordances codelet
- [ ] EDD Codelet
- [ ] SAM Codelet
- [ ] ToMM Codelet

### Open Questions
- [ ] Should MOs have information only for the current simulation step?
- [ ] Implement mechanism to update the MOs on the containers for each simulation step.
- [ ] How to maintain the mind step information across containers

### Test-Run for Sally-Anne
