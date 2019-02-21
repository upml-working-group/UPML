# UPML: Universal Phylogenetic Model Language

An attempt to specify phylogenetic models using a language based on JAGS for the non-phylogenetic part of the model.

The language is agnostic of the implementation or algorithm (MCMC, maximum likelihood, sequential Monte Carlo, etc) used for inference using the model. This implies any algorithm specific information (like which proposal mechanism are used for MCMC) are not part of the language.
